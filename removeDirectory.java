/*
->push mainFileName in stack
->process starts
	->pop an element from stack(dir)
	->if dir is deleteable:delete dir and do nothing (Here dir is either dir or file which are both deleteable)
	->traverse it's subDirectories(sdir)
		->if sdir is a directory: push it to stack
		->if it is a deleteAble file:delete it
*/
import java.util.*;
import java.io.*;
class removeDirectory
{
public static void main(String gg[])
{
if(gg.length!=1)
{
System.out.println("Input: java removeDirectory *directory Name*");
return;
}
String mainFileName=gg[0];
File mainDirFile=new File(mainFileName);
String currentFileName,currentDirName;
File t;
File files[];
Stack<File> stack=new Stack<>();
stack.push(mainDirFile);
Stack<File> emptyFolders=new Stack<>();
while(stack.size()>0)
{
t=stack.pop();
currentDirName=t.getAbsolutePath();
//System.out.println("Traversing: "+currentDirName);
if(t.delete())
{
System.out.println("Directory: "+currentDirName+" deleted");
continue;
}
else
{
if(t.isDirectory())
{
emptyFolders.push(t);
}
}
files=t.listFiles();
for(File sFile:files)
{
if(sFile.isDirectory())
{
stack.push(sFile);
}
else
{
currentFileName=sFile.getAbsolutePath();
if(sFile.delete())
{
System.out.println("File: "+currentFileName+" deleted");
}
}
}
}
File emptyFolder;
String emptyFolderName;
while(emptyFolders.size()>0)
{
emptyFolder=emptyFolders.pop();
emptyFolderName=emptyFolder.getAbsolutePath();
if(emptyFolder.delete())
{
System.out.println("Directory: "+emptyFolderName+" deleted");
}
}
}
}