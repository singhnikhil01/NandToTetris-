//push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
//push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
//eq
@SP
A=M
A=A-1
D=M
A=A-1
D=M-D
@SP
M=M-1
M=M-1
@eq_0
D;JEQ
@SP
A=M
M=0
@END_line0
0;JMP
(eq_0)
@SP
A=M
M=-1
(END_line0)
@SP
M=M+1
//push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
//push constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
//eq
@SP
A=M
A=A-1
D=M
A=A-1
D=M-D
@SP
M=M-1
M=M-1
@eq_1
D;JEQ
@SP
A=M
M=0
@END_line1
0;JMP
(eq_1)
@SP
A=M
M=-1
(END_line1)
@SP
M=M+1
//push constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
//push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
//eq
@SP
A=M
A=A-1
D=M
A=A-1
D=M-D
@SP
M=M-1
M=M-1
@eq_2
D;JEQ
@SP
A=M
M=0
@END_line2
0;JMP
(eq_2)
@SP
A=M
M=-1
(END_line2)
@SP
M=M+1
//push constant 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1
//push constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
//lt
@SP
A=M
A=A-1
D=M
A=A-1
D=M-D
@SP
M=M-1
M=M-1
@lt_3
D;JLT
@SP
A=M
M=0
@END_line3
0;JMP
(lt_3)
@SP
A=M
M=-1
(END_line3)
@SP
M=M+1
//push constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
//push constant 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1
//lt
@SP
A=M
A=A-1
D=M
A=A-1
D=M-D
@SP
M=M-1
M=M-1
@lt_4
D;JLT
@SP
A=M
M=0
@END_line4
0;JMP
(lt_4)
@SP
A=M
M=-1
(END_line4)
@SP
M=M+1
//push constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
//push constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
//lt
@SP
A=M
A=A-1
D=M
A=A-1
D=M-D
@SP
M=M-1
M=M-1
@lt_5
D;JLT
@SP
A=M
M=0
@END_line5
0;JMP
(lt_5)
@SP
A=M
M=-1
(END_line5)
@SP
M=M+1
//push constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
//push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
//gt
@SP
A=M
A=A-1
D=M
A=A-1
D=M-D
@SP
M=M-1
M=M-1
@gt_6
D;JGT
@SP
A=M
M=0
@END_line6
0;JMP
(gt_6)
@SP
A=M
M=-1
(END_line6)
@SP
M=M+1
//push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
//push constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
//gt
@SP
A=M
A=A-1
D=M
A=A-1
D=M-D
@SP
M=M-1
M=M-1
@gt_7
D;JGT
@SP
A=M
M=0
@END_line7
0;JMP
(gt_7)
@SP
A=M
M=-1
(END_line7)
@SP
M=M+1
//push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
//push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
//gt
@SP
A=M
A=A-1
D=M
A=A-1
D=M-D
@SP
M=M-1
M=M-1
@gt_8
D;JGT
@SP
A=M
M=0
@END_line8
0;JMP
(gt_8)
@SP
A=M
M=-1
(END_line8)
@SP
M=M+1
//push constant 57
@57
D=A
@SP
A=M
M=D
@SP
M=M+1
//push constant 31
@31
D=A
@SP
A=M
M=D
@SP
M=M+1
//push constant 53
@53
D=A
@SP
A=M
M=D
@SP
M=M+1
//add
@SP
A=M
A=A-1
D=M
A=A-1
D=M+D
@SP
M=M-1
M=M-1
A=M
M=D
@SP
M=M+1
//push constant 112
@112
D=A
@SP
A=M
M=D
@SP
M=M+1
//sub
@SP
A=M
A=A-1
D=M
A=A-1
D=M-D
@SP
M=M-1
M=M-1
A=M
M=D
@SP
M=M+1
//neg
@SP
M=M-1
A=M
D=M
@SP
A=M
M=-D
@SP
M=M+1
//and
@SP
A=M
A=A-1
D=M
A=A-1
D=D&M
@SP
M=M-1
M=M-1
A=M
M=D
@SP
M=M+1
//push constant 82
@82
D=A
@SP
A=M
M=D
@SP
M=M+1
//or
@SP
A=M
A=A-1
D=M
A=A-1
D=D|M
@SP
M=M-1
M=M-1
A=M
M=D
@SP
M=M+1
//not
@SP
M=M-1
A=M
D=M
@SP
A=M
M=!D
@SP
M=M+1