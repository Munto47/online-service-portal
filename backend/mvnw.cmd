@ECHO OFF
setlocal
set WRAPPER_DIR=%~dp0
set WRAPPER_JAR=%WRAPPER_DIR%\.mvn\wrapper\maven-wrapper.jar
set JAVA_EXE=%JAVA_HOME%\bin\java.exe
if not exist "%JAVA_HOME%" (
  set JAVA_EXE=java
)
"%JAVA_EXE%" -Dmaven.multiModuleProjectDirectory=%WRAPPER_DIR% -cp "%WRAPPER_JAR%" org.apache.maven.wrapper.MavenWrapperMain %*
