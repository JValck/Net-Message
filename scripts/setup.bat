@echo off

:: BatchGotAdmin
:-------------------------------------
REM  --> Check for permissions
    IF "%PROCESSOR_ARCHITECTURE%" EQU "amd64" (
>nul 2>&1 "%SYSTEMROOT%\SysWOW64\cacls.exe" "%SYSTEMROOT%\SysWOW64\config\system"
) ELSE (
>nul 2>&1 "%SYSTEMROOT%\system32\cacls.exe" "%SYSTEMROOT%\system32\config\system"
)

REM --> If error flag set, we do not have admin.
if '%errorlevel%' NEQ '0' (
    echo Requesting administrative privileges...
    goto UACPrompt
) else ( goto gotAdmin )

:UACPrompt
    echo Set UAC = CreateObject^("Shell.Application"^) > "%temp%\getadmin.vbs"
    set params = %*:"=""
    echo UAC.ShellExecute "cmd.exe", "/c ""%~s0"" %params%", "", "runas", 1 >> "%temp%\getadmin.vbs"

    "%temp%\getadmin.vbs"
    del "%temp%\getadmin.vbs"
    exit /B

:gotAdmin
    pushd "%CD%"
    CD /D "%~dp0"
:--------------------------------------
:SOURCE: http://stackoverflow.com/questions/1894967/how-to-request-administrator-access-inside-a-batch-file

echo Starting IP Configuration...
::ping subnet part to determine claimed ip-addresses
echo Scanning network.
echo Please wait, this might take some time...
::ping from 192.168.2.50 to 192.168.2.60
for /l %%i in (50,1,60) do ping -n 1 192.168.2.%%i

::get language of computer
for /f "delims=" %%G in ('powershell -Command "get-UIculture | select Name | %% {$_.Name}"') do set fulllang=%%G
::retreive first two characters
set langCode=%fulllang:~0,2%
echo Locale: %fulllang%
echo Language code: %langCode%

::get os version
for /f "tokens=4-5 delims=. " %%i in ('ver') do set version=%%i.%%j
::jump to correct part
if "%version%" == "6.3" (echo Windows 8.1)
if "%version%" == "6.2" (echo Windows 8.)
if "%version%" == "6.1" (echo Windows 7.)
if "%version%" geq "10.0" (
  goto win10
)
::end execution
goto:eof


::Windows 10 subroutine
:win10
  echo Detected Windows 10
  ::currently only English tested
  echo Current IP-configuration:
  echo -------------------------
  netsh interface ipv4 show addresses Ethernet
  echo Updating IP-address
  netsh interface ipv4 set address name=Ethernet source=static address=192.168.2.121 mask=255.255.255.0

  echo New IP-configuration:
  echo ---------------------
  netsh interface ipv4 show addresses Ethernet
goto:eof



pause
