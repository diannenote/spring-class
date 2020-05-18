-- Spring에서 DeptVO 사용  DeptVO 전달 받음 
Create or Replace Procedure Dept_Insert3
 (vdeptno   in  dept.deptno%type,    vdname in dept.dname% type,     vloc in dept.loc%Type, 
  p_deptno OUT  dept.deptno%type,    p_dname OUT  dept.dname%TYPE,   p_loc OUT dept.loc%TYPE)
 Is
 BEGIN
      INSERT  INTO  dept values(vdeptno , vdname, vloc);
      commit;
    
      DBMS_OUTPUT.ENABLE;
      -- %TYPE 데이터형 변수 사용
      SELECT   deptno,     dname,     loc
      INTO     p_deptno,   p_dname,   p_loc
      FROM     dept
      WHERE    deptno = vdeptno ;

      -- 결과값 출력
      DBMS_OUTPUT.PUT_LINE( '부서번호 : ' || p_deptno );
      DBMS_OUTPUT.PUT_LINE( '부서이름 : ' || p_dname );
      DBMS_OUTPUT.PUT_LINE( '부서위치 : ' || p_loc );
 End;
