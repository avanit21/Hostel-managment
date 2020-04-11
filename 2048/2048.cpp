#include<iostream>
#include<conio.h>
#include<cstdlib>
#include<time.h>
#include<windows.h>
#include<iomanip>
#include"gotoxy.h"

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
    int score=0;
public:
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
            gotoxy(50,35);
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
        char c;

        c=getche();
        cout<<endl;


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
                cout<<"your choice is wrong, please re-enter the right choice";
            }

        }

int main()
{
    G e;
    int o,j;
    gotoxy(0,0);
    drawline();
    Sleep(100);
    gotoxy(68,4);
    cout << " 2048 ";
    gotoxy(0,8);
    drawline();
    system("color 2e");


    srand(time(0));

    for(int i=0;i<1000 ;i++)
    {
        if(i<2)
        {
            o=e.gen();
            j=e.gen();
            e.intialize(o,j);
        }
        else
        {
            e.print();
            e.print_matrix();
            e.go();
            o=e.gen();
            j=e.gen();
            e.intialize(o,j);

        }
    }
}

