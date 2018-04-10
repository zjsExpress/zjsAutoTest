#Region ;**** 参数创建于 ACNWrapper_GUI ****
#PRE_Icon=ico.ico
#PRE_UseX64=n
#EndRegion ;**** 参数创建于 ACNWrapper_GUI ****
WinWaitActive($CmdLine[1])
WinActive($CmdLine[1])
ControlSetText($CmdLine[1],"","[CLASS:Edit;INSTANCE:1]",$CmdLine[2])
ControlClick($CmdLine[1],"","[CLASS:Button;INSTANCE:1]")
Send("!o")