import java.awt.*;
import java.awt.datatransfer.*;
import java.io.*;
import java.util.*;
class GetterSetterGenerator
{
public static void main(String gg[])
{
if(gg.length!=1)
{
System.out.println("Input: File Name");
return;
}
LinkedList<String> valueTypes=new LinkedList<String>();
LinkedList<String> propertyNames=new LinkedList<String>();
String fileName=gg[0];
StringBuffer sb=new StringBuffer();
StringBuffer outputString=new StringBuffer();
try
{
File file=new File(fileName);
if(file.exists()==false)
{
System.out.println("File does not exists");
return;
}
RandomAccessFile raf=new RandomAccessFile(fileName,"rw");
if(raf.length()==0)
{
System.out.println("File empty");
return;
}
String className="";
boolean ignore=false;
String inputString;
String property,state,type;
String splits[];
String sp[];
for(ignore=false;raf.length()>0;ignore=false)
{
System.out.println("loop");
inputString=raf.readLine();
if(inputString==null) break;
if(inputString.equals("")) ignore=true;
if(inputString.startsWith("package")) ignore=true; 
if(inputString.startsWith("import ")) ignore=true;
if(inputString.startsWith("class")) 
{
splits=inputString.split(" ");
className=splits[1];
ignore=true;
}
if(inputString.startsWith("public class"))
{
splits=inputString.split(" ");
className=splits[2];
ignore=true;
}
if(inputString.startsWith("{")) ignore=true;
if(inputString.startsWith("}")) ignore=true;
if(inputString.startsWith("@")) ignore=true;
if(ignore==true) continue;
splits=inputString.split(" ");
state=splits[0];
type=splits[1];
if(splits[2].equals("[]"))
{
System.out.println("array present as property");
return;
}
property=splits[2];
sp=property.split(";");
property=sp[0];
propertyNames.add(property);
valueTypes.add(type);
sb.append("public void set"+property.substring(0,1).toUpperCase()+property.substring(1)+"("+type+" "+property+")");
sb.append("\r\n");
sb.append("{");
sb.append("\r\n");
sb.append("this."+property+"="+property+";");
sb.append("\r\n");
sb.append("}");
sb.append("\r\n");
sb.append("public "+type+" get"+property.substring(0,1).toUpperCase()+property.substring(1)+"()");
sb.append("\r\n");
sb.append("{");
sb.append("\r\n");
sb.append("return this."+property+";");
sb.append("\r\n");
sb.append("}");
sb.append("\r\n");
}
outputString.append("public "+className+"()");
outputString.append("\r\n");
outputString.append("{");
outputString.append("\r\n");
for(int i=0;i<propertyNames.size();i++)
{
outputString.append("this."+propertyNames.get(i)+"=");
if(valueTypes.get(i).equals("int") || valueTypes.get(i).equals("short") || valueTypes.get(i).equals("byte") || valueTypes.get(i).equals("long") || valueTypes.get(i).equals("float") || valueTypes.get(i).equals("double")) outputString.append("0");
else if(valueTypes.get(i).equals("Integer") || valueTypes.get(i).equals("Short") || valueTypes.get(i).equals("Byte") || valueTypes.get(i).equals("Long") || valueTypes.get(i).equals("Float") || valueTypes.get(i).equals("Double")) outputString.append("0");
else if(valueTypes.get(i).equals("boolean")) outputString.append("false");
else if(valueTypes.get(i).equals("String")) outputString.append("\"\"");
else outputString.append("null"); //even for char
outputString.append(";\r\n");
}
outputString.append("}");
outputString.append("\r\n");
outputString.append(sb.toString());
System.out.println(outputString.toString());

StringSelection codeString=new StringSelection(outputString.toString());
Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
clipboard.setContents(codeString,codeString);
raf.close();
}catch(IOException ioException)
{
//raf.close();
System.out.println(ioException);
}
}
}