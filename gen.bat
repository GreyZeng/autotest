@echo off
set now=%cd%
::����ִ��vs���ñ�����(cl.exe)���軷���Ľű�
call "C:\Program Files (x86)\Microsoft Visual Studio\2017\Community\VC\Auxiliary\Build\vcvarsall.bat" x86
::��ע�ͣ��Ա�鿴���
::echo on 
::��Ҫ!��Ϊvs���û��������Ľű����Զ��е�c:\Users\..\source
::���������Ҫ�лص����ǵĵ�ǰ��Ŀ������ʵ����ֱ��e:�Ϳ��Իص��������·����)
e: 

::����gen.txt�е�ÿһ�У�ÿһ��ѧ������Ŀ·������������exe
for /f "delims=" %%a in (gen.txt) do (
    :: �ܵ���ͬѧ��Ŀ¼��
    cd %dp~0%%%a
    ::ָ�����ɵ�exe�ļ���Ϊwc.exe,�ڸ�ͬѧ�ĵ�ǰĿ¼��
    cl *.cpp /EHsc /Fewc.exe
    ::�ص���Ŀ��Ŀ¼
    cd %now%
    endlocal
)
