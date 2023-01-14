import java.util.*;
class Sudoku {
static int a[][] = new int[9][9]; //answer input array
static int f[][] = new int [9][9]; //checker array

Sudoku()
{
for (int i=0; i<9; i++)
{
for(int j=0; j<9; j++)
{
f[i][j]=0; a[i][j]=0;
}
}
f[0][0]=(int)(Math.random() * ((9 - 1) + 1)) + 1;
f[0][5]=(int)(Math.random() * ((9 - 1) + 1)) + 1;
f[8][8]=(int)(Math.random() * ((9 - 1) + 1)) + 1;
}

boolean chk(int x[][], int r, int c, int num)//checks availability
{
//row check
for(int i=0; i<9; i++)
{
if(x[r][i]==num)
return false;
}
//column check
for(int i=0; i<9; i++)
{
if(num==x[i][c])
return false;
}
//box check
int sqrt = (int) Math.sqrt(x.length);
int rowStart = r - r % sqrt;
int colStart = c - c % sqrt;

for (int p = rowStart;
p < rowStart + sqrt; p++)  
{
for (int d = colStart;  
d < colStart + sqrt; d++)  
{
if (x[p][d] == num)  
{
return false;
}
}
}
return true;

}

boolean generator(int x[][])//generates answer array
{
int row=-1, col=-1;
boolean notFree=true;
for(int i=0; i<9; i++)
{
for(int j=0; j<9; j++)
{
if(x[i][j]==0)
{
row=i; col=j;
notFree=false;//empty space present
break;
}
}
if(!notFree)
break;
}
if(notFree) {
return (true);//no empty space
}
//backtrack for number input
for(int i=1; i<=9; i++)
{
if(chk(f, row, col, i))
{
x[row][col]=i;
if(generator(x)) {
return true;}
else
f[row][col]=0;
}
}
return false;
}

void print(int[][] x)//prints array
{

for (int r = 0; r < 9; r++)
{
for (int d = 0; d < 9; d++)
{
System.out.print(x[r][d]+" ");
System.out.print(" ");
}
System.out.println();
}
}

int[][] main()
{

Sudoku ob = new Sudoku();
int x ;
if(ob.generator(f)==true)
x=0;

for(int i=1; i<=30; i++)//generating input array
{
int r=(int)(Math.random() * ((8 - 0) + 0)) + 0;
int c=(int)(Math.random() * ((8 - 0) + 0)) + 0;
if(a[r][c]==0)
a[r][c]=f[r][c];
else {
--i; continue;
}
}
return a ;

}
int [][] answer()
{print(f);
    return f;
}
}
