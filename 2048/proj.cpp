#include<iostream>
#include<conio.h>
#include<cstdlib>
#include<time.h>
#include<windows.h>
#include<iomanip>
#include"gotoxy.h"
#include<fstream>
int game();
char x;

using namespace std;
void drawline()
{
    for(int i=0;i<150;i++)
    {
        Sleep(1);
        cout << "_";
    }
}
class G
{
    int a[5][5],t=2;

public:
    int score=0;
    G()
    {
        t=2;
        for(int j=0;j<5;j++)
        {
            for(int k=0;k<5;k++)
            {
                a[j][k]=0;
            }
        }

    }
    int print_matrix()
    {
        for(int r=1;r<18;r++)
        {
            if(((r-1)%4)==0)
            {
                gotoxy(55,12+r);
                cout << "---------------------------------";
            }
        }
        for(int r=3;r<8;r++)
        {
            for(int l=13;l<30;l++)
            {
                gotoxy((8*r)+31,l);
                cout << "|";
            }
        }
    }

    int print()
    {
         system("color 5e");


        for(int p=1;p<5;p++)
        {
            for(int u=1;u<5;u++)

            {

                gotoxy((43+(u*8)),(11+(p*4)));
                if(a[p][u]==0)
                {
                    cout<<setw(8)<<" ";

                }
                else
                    cout<<setw(8)<<a[p][u];

            }
            cout<<endl;
            gotoxy(40,20);
            cout<<"SCORE "<<score;
        }

    }
    int gen()
    {

        int m;
        m=(rand()%5);
        if(m==0)
            m++;
        else
            m;

        return m;

    }
    int intialize(int k, int z);
    int go();
    int check();


};
int G::intialize(int k, int z)
    {
        int y=0;
        if(a[k][z]==0)
            {a[k][z]=t;

            }
        else
            {
            k=gen();
            z=gen();
            intialize(k,z);
            }
    }


int G::go()
    {
        lable:
        char c;
        gotoxy(89,29);
        c=getche();
        gotoxy(89,29);
        cout << " ";
        gotoxy(30,40);
        cout<<"                                                      ";

        if(c=='d')
            {


                for(int c=1;c<5;c++)
                {

                    int k=0;
                    for(int g=1;g<4;g++)
                    {
                        if(a[c][g]==a[c][g+1]||a[c][g+1]==0)
                        {

                            if(a[c][g]==a[c][g+1])
                            {
                                if(k==g)
                                    continue;
                                else
                                {
                                 a[c][g+1]+=a[c][g];
                                 a[c][g]=0;
                                 score+=a[c][g+1];
                                 for(int j=g;j>1;j--)
                                 {
                                     a[c][j]=a[c][j-1];
                                     a[c][j-1]=0;
                                 }


                                 k=g+1;
                                }
                            }
                            else if(a[c][g+1]==0)
                            {

                            int j=a[c][g+1];
                            a[c][g+1]=a[c][g];
                            a[c][g]=j;
                            for(int j=g;j>1;j--)
                                 {
                                     a[c][j]=a[c][j-1];
                                     a[c][j-1]=0;
                                 }

                             if(k!=0)
                                k=g+1;
                            else
                                continue;
                            }
                            else
                                {continue;}

                        }
                        else
                            continue;




                    }
                }
            }
        else if(c=='w')
            {

                for(int g=1;g<5;g++)
                {
                    int k=0;

                    for(int c=4;c>1;c--)
                    {
                        if(a[c][g]==a[c-1][g]||a[c-1][g]==0)
                        {

                            if(a[c][g]==a[c-1][g])
                            {
                                if(k==c)
                                    continue;
                                else
                                {
                                 a[c-1][g]+=a[c][g];
                                 a[c][g]=0;
                                 score+=a[c-1][g];
                                 k=c-1;
                                 for(int j=c;j<4;j++)
                                 {
                                     a[j][g]=a[j+1][g];
                                     a[j+1][g]=0;
                                 }
                                }
                            }
                            else if(a[c-1][g]==0)
                            {

                            int j=a[c][g];
                            a[c][g]=a[c-1][g];
                            a[c-1][g]=j;
                             for(int j=c;j<4;j++)
                                 {
                                     a[j][g]=a[j+1][g];
                                     a[j+1][g]=0;
                                 }

                            if(k!=0)
                                k=c-1;
                            else

                              continue;
                            }
                            else
                                {
                                    continue;
                                }


                        }
                        else
                            continue;



                    }
                }
            }
        else if(c=='s')
            {
               for(int g=1;g<5;g++)
                {
                    int k=0;

                    for(int c=1;c<4;c++)
                    {
                        if(a[c][g]==a[c+1][g]||a[c+1][g]==0)
                        {

                            if(a[c][g]==a[c+1][g])
                            {
                                if(k==c)
                                    continue;
                                else
                                {
                                 a[c+1][g]+=a[c][g];
                                 a[c][g]=0;
                                 score+=a[c+1][g];
                                  k=c+1;
                                 for(int j=c;j>1;j--)
                                 {
                                    a[j][g]=a[j-1][g];
                                    a[j-1][g]=0;
                                 }

                                }
                            }
                            else if(a[c+1][g]==0)
                            {

                            int j=a[c][g];
                            a[c][g]=a[c+1][g];
                            a[c+1][g]=j;
                            for(int j=c;j>1;j--)
                                 {
                                    a[j][g]=a[j -1][g];
                                    a[j-1][g]=0;
                                 }

                            if(k!=0)
                                k=c+1;
                            else
                                continue;
                            }
                            else
                                {continue;}

                        }
                        else
                            continue;


                    }
                }
            }
        else if(c=='a')
            {
                for(int c=1;c<5;c++)
                {
                    int k=0;
                    for(int g=4;g>1;g--)
                    {
                        if(a[c][g]==a[c][g-1]||a[c][g-1]==0)
                        {

                            if(a[c][g]==a[c][g-1])
                            {
                                if(k==g)
                                    continue;
                                else
                                {
                                 a[c][g-1]+=a[c][g];
                                 a[c][g]=0;
                                 score+=a[c][g-1];
                                 k=g-1;
                                 for(int j=g;j<4;j++)
                                 {
                                     a[c][j]=a[c][j+1];
                                     a[c][j+1]=0;
                                 }
                                }
                            }
                            else if(a[c][g-1]==0)
                            {

                            int j=a[c][g-1];
                            a[c][g-1]=a[c][g];
                            a[c][g]=j;
                            for(int j=g;j<4;j++)
                                 {
                                     a[c][j]=a[c][j+1];
                                     a[c][j+1]=0;
                                 }
                            if(k!=0)
                                k=g-1;
                            else
                                continue;
                            }
                            else
                                {continue;}


                        }
                        else
                            continue;




                    }
                }
            }
            else
            {
                gotoxy(30,40);
                cout<<"your choice is wrong, please re-enter the right choice";
                goto lable;
            }

        }
game()
{
     G e;
        int o,j;
        char y;

 gotoxy(0,0);
    drawline();
    Sleep(1);
    gotoxy(55,3);
    cout << " 22222   00000   4   4   88888";
    gotoxy(55,4);
    cout << "     2   0   0   4   4   8   8";
    gotoxy(55,5);
    cout << " 22222   0   0   44444   88888";
    gotoxy(55,6);
    cout << " 2       0   0       4   8   8";
    gotoxy(55,7);
    cout << " 22222   00000       4   88888";
    gotoxy(0,8);
    drawline();
    system("color 2e");


    srand(time(0));

    for(int i=0;i<1000 ;i++)
    {
        if(i<1)
        {
            o=e.gen();
            j=e.gen();
            e.intialize(o,j);
        }
        else
        {


            o=e.gen();
            j=e.gen();
            e.intialize(o,j);
            e.print();
            e.print_matrix();
            e.check();
            e.go();
            if(x=='n')
            {
                i=1000;
            }
            else
                continue;

        }
    }
}
int G::check()
{

    int k=0;

   for(int row=4;row>0;row--)
   {

       for(int colum=4;colum>0;colum--)
       {

           if((a[row][colum]==a[row][colum-1] )|| (a[row][colum]==a[row-1][colum]))
           {

               continue;
           }
           else if(a[row][colum]!=0)
            {

                k++;

            }
            else if(a[row][colum]==2048)
            {
                k=16;
                break;
                system("cls");
                cout<<"You won";
            }
       }
   }
   if(k==16)
   {

       system("cls");
       gotoxy(30,10);
       cout<<"game over ";
       gotoxy(30,15);
       cout<<"You want to play again press(y or n)";

       gotoxy(30,10);
       label2:
       x=getche();
       if(x=='y')
       {
           system("cls");
           game();
       }
       else if(x=='n')
       {
           gotoxy(30,20);
           cout<<"Your score is :"<<score;

       }
       else
       {
           cout<<"Your choice is wrong please enter right choice.";
           goto label2;
       }



   }

}


int main()
{
    game();

}

