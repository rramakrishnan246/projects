//created by Andy Hutchison and Rohan Ramakrishnan

public class Board
{
    int [][] positions = new int[15][43];
   
    public Board()
    {
        
    }
    
     public void BaseValue()
    {
        for (int x=0; x<15; x++)
            {
                for (int y=0; y<43; y++)
                    {
                        positions[x][y] = 1;
                    }
            } 
    }
    
     public int[][] makeObstacles()
    {
        //top obstacles
        BaseValue();
        positions[1][1]=2;
        positions[2][1]=2;
        positions[3][36]=2;
        positions[4][8]=2;
        for (int o1=3; o1<7; o1++) {
            positions[0][o1]=2;
            positions[2][o1]=2;
        }
        for (int o2=8; o2<11; o2++){
            positions[1][o2]=2;
            positions[2][o2]=2;
        }
        for (int o3=0; o3<3; o3++) positions[o3][12]=2;
        for (int o4=22; o4<25; o4++) positions[1][o4]=2;
        for (int o5=22; o5<25; o5++) positions[2][o5]=2;
        for (int o6=26; o6<29; o6++) positions[1][o6]=2;
        for (int o7=26; o7<29; o7++) positions[2][o7]=2;
        for (int o8=0; o8<3; o8++) positions[o8][30]=2;
        for (int o9=0; o9<3; o9++) positions[o9][31]=2;
        
        for (int o10=35; o10<37; o10++) positions[1][o10]=2;
        for (int o11=40; o11<42; o11++) positions[1][o11]=2;
        for (int o12=40; o12<42; o12++) positions[2][o12]=2;
        for (int o13=0; o13<3; o13++) positions[o13][38]=2;
        
        for (int o14=4; o14<7; o14++) positions[o14][5]=2;
        for (int o15=4; o15<7; o15++) positions[o15][6]=2;
        
        //bottom obstacles
        for (int o16=1; o16<5; o16++) positions[12][o16]=2;
        for (int o17=1; o17<5; o17++) positions[13][o17]=2;
        for (int o18=11; o18<14; o18++) positions[o18][6]=2;
        for (int o19=8; o19<13; o19++) positions[12][o19]=2;
        for (int o20=8; o20<13; o20++) positions[14][o20]=2;
        for (int o21=12; o21<14; o21++) positions[o21][14]=2;
        for (int o22=11; o22<14; o22++) positions[o22][16]=2;
        for (int o23=11; o23<14; o23++) positions[o23][17]=2;
        
        for (int o24=19; o24<21; o24++) positions[12][o24]=2;
        for (int o25=19; o25<21; o25++) positions[13][o25]=2;
        for (int o26=22; o26<24; o26++) positions[12][o26]=2;
        for (int o27=22; o27<24; o27++) positions[13][o27]=2;
        
        for (int o28=12; o28<15; o28++) positions[o28][26]=2;
        for (int o29=12; o29<14; o29++) positions[o29][28]=2;
        for (int o30=11; o30<13; o30++) positions[o30][30]=2;
        for (int o31=11; o31<14; o31++) positions[o31][36]=2;
        
        for (int o32=12; o32<14; o32++) positions[o32][38]=2;
        for (int o33=12; o33<14; o33++) positions[o33][39]=2;
        for (int o34=12; o34<14; o34++) positions[o34][41]=2;
        
        for (int o35=32; o35<34; o35++) positions[12][o35]=2;
        for (int o36=30; o36<33; o36++) positions[14][o36]=2;
        positions[13][34]=2;
        positions[12][34]=2;
        positions[1][33]=2;
        positions[2][33]=2;
        positions[3][35]=2;
        positions[13][24]=2;
        positions[12][24]=2;
        
        SpellLancers();
        clearInner();
        ghostsCage();
        return positions;
    }
    
    public void SpellLancers()
    {
        //most logical way to spell out "LANCERS" w/o doing each individual square...
        
        //2 sides
        for (int c=4; c<11; c++){ positions[c][1]=3; }
        for (int j=4; j<11; j++){ positions[j][41]=3; }
        
        //botom squares
        for (int d=1; d<7; d++){ positions[10][d]=3; }
        for (int e=8; e<11; e++){ positions[10][e]=3; }
        for (int f=12; f<17; f++){ positions[10][f]=3; }
        for (int g=18; g<21; g++){ positions[10][g]=3; }
        for (int h=22; h<31; h++){ positions[10][h]=3; }
        for (int i=32; i<42; i++){ positions[10][i]=3; }
        
        //top squares
        for (int k=1; k<4; k++){ positions[4][k]=3; }
        positions[4][11]=3;
        positions[4][14]=3;
        positions[4][15]=3;
        for (int l=18; l<21; l++){ positions[4][l]=3; }
        for (int m=22; m<31; m++){ positions[4][m]=3; }
        for (int n=32; n<41; n++){ positions[4][n]=3; }
        
        //inside vertical lines
        for (int p=4; p<11; p++){ 
            positions[p][14]=3;  
            positions[p][20]=3;
            positions[p][22]=3;
            positions[p][26]=3;
            positions[p][30]=3;
            positions[p][32]=3;
            positions[p][36]=3;
        }
        
        //straggler pieces
        for (int q=4; q<9; q++){ positions[q][3]=3;}
        for (int r=4; r<7; r++){ positions[8][r]=3;}
        positions[9][6]=3;
        
        //A
        positions[6][9]=3;
        positions[7][9]=3;
        positions[5][10]=3;
        positions[4][11]=3;
        positions[5][12]=3;
        positions[6][13]=3;
        positions[7][13]=3;
        positions[7][11]=3;
        positions[8][8]=3;
        positions[9][8]=3;
        positions[9][10]=3;
        positions[9][11]=3;
        positions[9][12]=3;
        
        //N
        positions[5][16]=3;
        positions[6][17]=3;
        positions[8][16]=3;
        positions[9][16]=3;
        positions[9][17]=3;
        for(int s=4; s<8; s++) positions[s][18]=3;
        
        //c
        positions[6][24]=3;
        positions[6][25]=3;
        positions[7][24]=3;
        positions[8][24]=3;
        positions[8][25]=3;
        
        //e
        positions[6][28]=3;
        positions[6][29]=3;
        positions[8][28]=3;
        positions[8][29]=3;
        
        //r
        positions[6][34]=3;
        positions[8][35]=3;
        positions[9][34]=3;
        
        //s
        for(int t=38; t<41; t++) positions[6][t]=3;
        for(int u=37; u<40; u++) positions[8][u]=3;
    }
    
    public void clearInner()
    {
        for (int in1=5; in1<10; in1++) positions[in1][2]=4;
        for (int in2=5; in2<10; in2++) positions[in2][15]=4;
        for (int in3=5; in3<10; in3++) positions[in3][19]=4;
        for (int in4=5; in4<10; in4++) positions[in4][23]=4;
        for (int in5=5; in5<10; in5++) positions[in5][27]=4;
        for (int in6=5; in6<10; in6++) positions[in6][33]=4;
        
        for (int in7=37; in7<41; in7++) positions[5][in7]=4;
        for (int in8=37; in8<41; in8++) positions[7][in8]=4;
        for (int in9=37; in9<41; in9++) positions[9][in9]=4;
        for (int in10=3; in10<6; in10++) positions[9][in10]=4;
        for (int in11=9; in11<14; in11++) positions[8][in11]=4;
        
        for (int in12=10; in12<13; in12++) positions[6][in12]=4;
        for (int in13=16; in13<18; in13++) positions[7][in13]=4;
        for (int in14=17; in14<19; in14++) positions[8][in14]=4;
        
        for (int in15=24; in15<26; in15++) positions[5][in15]=4;
        for (int in16=24; in16<26; in16++) positions[9][in16]=4;
        for (int in17=28; in17<30; in17++) positions[5][in17]=4;
        for (int in18=28; in18<30; in18++) positions[7][in18]=4;
        for (int in19=28; in19<30; in19++) positions[9][in19]=4;
        for (int in20=34; in20<36; in20++) positions[5][in20]=4;
        for (int in21=34; in21<36; in21++) positions[7][in21]=4;
        
        for (int in22=15; in22<21; in22++) positions[1][in22]=4;
        for (int in23=14; in23<21; in23++) positions[3][in23]=4;
        for (int in24=0; in24<4; in24++) positions[in24][13]=4;
        for (int in25=0; in25<4; in25++) positions[in25][21]=4;
        
        
        positions[10][11]=3;
        positions[9][9]=4;
        positions[9][13]=4;
        positions[7][10]=4;
        positions[7][12]=4;
        positions[5][11]=4;
        positions[6][16]=4;
        positions[9][18]=4;
        positions[6][35]=4;
        positions[8][34]=4;
        positions[9][35]=4;
        positions[6][37]=4;
        positions[8][40]=4;
        positions[10][17]=3;
        positions[7][25]=3;
        positions[0][0]=4;
        positions[4][16]=4;
        positions[4][17]=4;
        positions[5][17]=4;
        positions[2][17]=2;
        
    }
    
    public void ghostsCage()
    {
        for (int g1=14; g1<21; g1++)positions[0][g1]=5;
        for (int g2=14; g2<21; g2++)positions[2][g2]=5;
        positions[1][14]=5;
        positions[1][20]=5; 
    }
    
    
}
