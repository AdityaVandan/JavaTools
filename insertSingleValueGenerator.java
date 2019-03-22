import java.io.*;
import java.util.*;
import java.awt.datatransfer.*;
import java.awt.*;
class insertSingleValueGenerator
{
public static void main(String gg[])
{
if(gg.length!=1)
{
System.out.println("Input Type -> java insertSingleValueGenerator filename.txt");
return;
}
try
{
File file=new File(gg[0]);
if(file.exists()==false)
{
System.out.println("File "+gg[0]+" does not exist");
return;
}
//System.out.println("1");
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
System.out.println("empty file");
return;
}
//System.out.println("2");
LinkedList<String> valueList=new LinkedList<>();
String tableName="";
String attributeName="";
StringBuffer sb=new StringBuffer();
String entity="";
boolean firstTime=true;
while(randomAccessFile.length()>0)
{
//System.out.println("3");
if(firstTime)
{
tableName=randomAccessFile.readLine();
System.out.println("Table: "+tableName);
attributeName=randomAccessFile.readLine();
System.out.println("Column: "+attributeName);
firstTime=false;
}
entity=randomAccessFile.readLine();
valueList.add(entity);
System.out.println("value-> "+entity);
if(entity==null) break;
sb.append("insert into "+tableName+" ("+attributeName+") values ('"+entity+"');\n");
}
System.out.println(sb.toString());
StringSelection codeString=new StringSelection(sb.toString());
Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
clipboard.setContents(codeString,codeString);
randomAccessFile.close();
}catch(IOException e)
{
System.out.println(e);
//randomAccessFile.close();
}
}
}