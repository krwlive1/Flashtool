# Auto-generated by EclipseNSIS Script Wizard
# 11 nov. 2011 22:34:19

Name Flashtool

SetCompressor /SOLID lzma

RequestExecutionLevel highest

# General Symbol Definitions
!define REGKEY "SOFTWARE\$(^Name)"
!define VERSION 0.8.6.0
!define COMPANY Androxyde
!define URL http://androxyde.github.com/Flashtool/

# MUI Symbol Definitions
!define MUI_ICON "${NSISDIR}\Contrib\Graphics\Icons\modern-install-colorful.ico"
!define MUI_FINISHPAGE_NOAUTOCLOSE
#!define MUI_STARTMENUPAGE_REGISTRY_ROOT HKLM
#!define MUI_STARTMENUPAGE_REGISTRY_KEY ${REGKEY}
#!define MUI_STARTMENUPAGE_REGISTRY_VALUENAME StartMenuGroup
#!define MUI_STARTMENUPAGE_DEFAULTFOLDER Flashtool
!define MUI_UNICON "${NSISDIR}\Contrib\Graphics\Icons\modern-uninstall-colorful.ico"
!define MUI_UNFINISHPAGE_NOAUTOCLOSE

# Included files
!include Sections.nsh
!include MUI2.nsh

# Installer pages
!insertmacro MUI_PAGE_WELCOME
!insertmacro MUI_PAGE_INSTFILES
!insertmacro MUI_PAGE_FINISH
#!insertmacro MUI_UNPAGE_CONFIRM
#!insertmacro MUI_UNPAGE_INSTFILES

# Installer languages
!insertmacro MUI_LANGUAGE English

# Installer attributes
OutFile ..\deploy\flashtool-update-0.8.x-${VERSION}-windows.exe
CRCCheck on
XPStyle on
ShowInstDetails show
VIProductVersion 0.8.6.0
VIAddVersionKey ProductName Flashtool
VIAddVersionKey ProductVersion "${VERSION}"
VIAddVersionKey CompanyName "${COMPANY}"
VIAddVersionKey CompanyWebsite "${URL}"
VIAddVersionKey FileVersion "${VERSION}"
VIAddVersionKey FileDescription ""
VIAddVersionKey LegalCopyright ""
InstallDirRegKey HKLM "${REGKEY}" Path
ShowUninstDetails show

# Installer sections
Section -Flashtool SEC0000
    ReadRegStr $INSTDIR HKLM "${REGKEY}" Path
    StrCmp $INSTDIR "" canceled continue
canceled:
  MessageBox MB_OK|MB_ICONEXCLAMATION \
  "$(^Name) is not installed. $\n$\nThe installation \
  will be aborted" \
  IDOK aborted
aborted:
  Abort
continue:
    SetOutPath $INSTDIR
    SetOverwrite on
    File ..\Deploy\FlashTool\x10flasher.jar
    File ..\Deploy\FlashTool\FlashTool.exe
    File ..\Deploy\FlashTool\FlashTool64.exe
    File ..\Deploy\FlashTool\FlashTool
    File ..\Deploy\FlashTool\FlashToolConsole
    File ..\Deploy\FlashTool\custom\features\NewUnlockBL\feature.properties
    WriteRegStr HKLM "${REGKEY}\Components" Flashtool 1
SectionEnd

# Installer functions
Function .onInit
  InitPluginsDir
FunctionEnd