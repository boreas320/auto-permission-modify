# auto-permission-modify
Auto set x  permission to files which are just created in the specific folder.

Required:
    JDK 7+

Usage:
    javac AutoModifyPermissions.java
    nohup java AutoModifyPermissions ${targetDirectory} &

    ${targetDirectory} is the directory in which the files you have created should be added with executable 
    permission.
    
NOTE:
    Now just auto X permission to javascript(.js) files.

