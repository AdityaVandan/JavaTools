import java.io.*;
import java.awt.datatransfer.*;
import java.awt.*;
class HtmlToPrintWriter
{
public static String validateString(String currentLine)
{
//System.out.println(currentLine.contains("\""));
if(currentLine.contains("\"")==true)
{
String currentString=currentLine;
String add="\\"+"\"";
String validString=currentString.replace("\"",add);
System.out.println("2");
return validString;
}
//System.out.println("1");
return currentLine;
}
public static void main(String gg[])
{
try
{
if(gg.length!=1)
{
System.out.println("Input Type: java HtmlToPrintWriter \"fileName\"");
return;
}
File file=new File(gg[0]);
if(file.exists()==false)
{
System.out.println("File does not exist");
return;
}
RandomAccessFile raf=new RandomAccessFile(file,"rw");
if(raf.length()==0)
{
System.out.println("File is of zero length");
return;
}
String inputString="";
String validString="";
String finalString="";
StringBuffer sb=new StringBuffer();
while(raf.getFilePointer()<raf.length())
{
inputString=raf.readLine();
validString=validateString(inputString);
finalString="pw.println(\""+validString+"\");\r\n";
sb.append(finalString);
}
System.out.println(sb.toString());
StringSelection codeString=new StringSelection(sb.toString());
Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
clipboard.setContents(codeString,codeString);
raf.close();
}catch(Exception e)
{
System.out.println(e);
}
}
}