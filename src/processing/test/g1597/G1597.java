package processing.test.g1597;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import apwidgets.*; 

import java.util.HashMap;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class G1597 extends PApplet {

/*
This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.
 
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the i//mplied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

//global variables declaration
int[] green ={color (193, 255, 193), color (144, 238, 144), color (50, 205, 50), color (0, 251, 0), color (173, 255, 47), color (34, 139, 34), color (0, 100, 0), color (107, 142, 35), color (128, 128, 0), color (143, 188, 143), color (46, 139, 87), color (152, 251, 152), color (152, 251, 152), color (152, 251, 152), color (152, 251, 152), color (152, 251, 152)} ;
int[] bluu = {color (198, 226, 255), color (185, 211, 238), color (188, 210, 238), color (100, 149, 237), color (72, 118, 255), color (67, 110, 238), color (58, 95, 205), color (39, 64, 139), color (0, 0, 255), color (0, 0, 238), color (0, 0, 205), color (0, 0, 139), color (0, 0, 128), color (198, 226, 255), color (198, 226, 255), color (198, 226, 255)};
int[] k= {color (245, 245, 245), color (245, 245, 220), color (255, 160, 122), color (255, 127, 80), color (255, 91, 71), color (255, 0, 0), color (255, 240, 96), color (240, 224, 80), color (240, 224, 16), color (240, 208, 0), color (132, 132, 132), color (144, 144, 144), color (156, 156, 156), color (168, 168, 168), color (180, 180, 180), color (192, 192, 192), color (204, 204, 204)};
int[] black = {color (227, 227, 227), color (224, 224, 224), color (222, 222, 222), color (217, 217, 217), color (212, 212, 212), color (199, 199, 199), color (166, 166, 166), color (158, 158, 158), color (135, 135, 135), color (117, 117, 117), color (110, 110, 110), color (105, 105, 105), color (84, 84, 84), color (79, 79, 79), color (227, 227, 227), color (227, 227, 227)};
int[] values={1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733};
boolean standard = true;
boolean green_color = false; 
boolean black_color=false;
boolean blu=false;
boolean sound_control=true;
int riga;
byte comodo, comodo1, comodo2, comodo3, comodo4=0;
int colonna;
float c1;
float c2;
float c3;
float c4;
boolean com=true;
boolean esegui = true;
float Ymouse, Xmouse;
Quadrato[][] w = new Quadrato[4][4];
boolean cambiamento = false;
Quadrato[][]griglia=new Quadrato[4][4] ;
int standard_color=color (205, 193, 180);
final boolean standard_assegnato=false; 
PFont myFont;
Quadrato score;
boolean start_game, settings, info, quit=false;
float larghezza;
float altezza;
PImage img, img2, img3, setting, infos, settingl, infosl, yes, no, cel, ver, sta, ner, perdita;
Point[] punti = new Point[50];
float textsize=0;
APMediaPlayer player;  
APMediaPlayer player1;
APMediaPlayer player2;

// this is the code from android "immersive mode"
@Override
public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);

    if (hasFocus) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    );
        }
    }
}

//a little welcome message
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Toast.makeText(getBaseContext(), "Have Fun And TRY To Defeat Master Fibonacci :) !", Toast.LENGTH_LONG).show();
}


public void onBackPressed() {
  mouseX = mouseY = 0; // <-- TOP !!
  if (!start_game && (!settings && !info)) {
    exit();
  }

  if (start_game) {
    start_game=false;
    com=true;
  }
  if (settings) {
    settings=false;
  }
  if (info) {
    info=false;
  }

}


public void setup() {
  myFont = createFont("paperino.ttf", 28);
  c1=width/6;
  c2=height/6;
  c3=width/6;
  c4=height/6;
  altezza=height;
  larghezza=width;
  player= new APMediaPlayer(this);
  player1= new APMediaPlayer(this);
  player2= new APMediaPlayer(this);
  player.setMediaFile("opening.mp3");
  player1.setMediaFile("beep.mp3");
  player2.setMediaFile("lose.mp3");
  if (sound_control) {

    player.setVolume(1.0f, 1.0f);
    player1.setVolume(1.0f, 1.0f);
    player2.setVolume(1.0f, 1.0f);
  }


  if (height*width<=384000) {
    if (altezza>larghezza) {
      textsize = c3/3;
    } else {
      textsize = c4/3;
    }

    img = loadImage("rsz_start.png");
    img2 = loadImage("rsz_voidlandscapestart.png");
    img3 = loadImage("rsz_black.jpeg");
    setting = loadImage("rsz_settings.png");
    settingl = loadImage("rsz_settingslandscape.png");
    infos = loadImage("rsz_info.png");
    infosl = loadImage("rsz_infolandscape.png");
  } else {
    if (altezza>larghezza) {
      textsize = c3/3;
    } else {
      textsize = c4/3;
    }
    img = loadImage("start.png");
    img2 = loadImage("voidlandscapestart.png");
    img3 = loadImage("black.jpeg");
    setting = loadImage("settings.png");
    settingl = loadImage("settingslandscape.png");
    infos = loadImage("info.png");
    infosl = loadImage("infolandscape.png");
  }
  yes = loadImage("yep.png");
  no = loadImage("nope.png");
  cel = loadImage("azzurro.png");
  ver = loadImage("verde.png");
  sta = loadImage("std.png");
  ner = loadImage("nero.png");
  perdita = loadImage("lose.png");
  textFont(myFont);



  for (int i=0; i<punti.length; i++) {
    punti[i]=new Point();
  }
  //punti chiave per la prima foto --> menu dritto
  if (height*width<=384000) {
    punti[0].setX((332*larghezza)/1242);
    punti[0].setY((896*altezza)/2208);
    punti[1].setX((888*larghezza)/1242);
    punti[1].setY((1040*altezza)/2208);
    punti[2].setX((364*larghezza)/1242);
    punti[2].setY((1136*altezza)/2208);
    punti[3].setX((848*larghezza)/1242);
    punti[3].setY((1256*altezza)/2208);
    punti[4].setX((444*larghezza)/1242);
    punti[4].setY((1380*altezza)/2208);
    punti[5].setX((768*larghezza)/1242);
    punti[5].setY((1533*altezza)/2208);
    punti[6].setX((468*larghezza)/1242);
    punti[6].setY((1633*altezza)/2208);
    punti[7].setX((801*larghezza)/1242);
    punti[7].setY((1782*altezza)/2208);

    //punti chiave per la seconda foto --> menu landscape
    punti[8].setX(((904*larghezza)/2208)/2);
    punti[8].setY(((468*altezza)/1242)/2);
    punti[9].setX(((1286*larghezza)/2208)/2);
    punti[9].setY(((574*altezza)/1242)/2);
    punti[10].setX(((918*larghezza)/2208)/2);
    punti[10].setY(((600*altezza)/1242)/2);
    punti[11].setX(((1286*larghezza)/2208)/2);
    punti[11].setY(((700*altezza)/1242)/2);
    punti[12].setX(((974*altezza)/1242)/2);
    punti[12].setY(((726*larghezza)/2208)/2);
    punti[13].setX(((1260*altezza)/1242)/2);
    punti[13].setY(((826*larghezza)/2208)/2);
    punti[14].setX(((980*altezza)/1242)/2);
    punti[14].setY(((986*larghezza)/2208)/2);
    punti[15].setX(((1244*altezza)/1242)/2);
    punti[15].setY(((1096*larghezza)/2208)/2);
    //punti per il back settings
    //dritto
    punti[16].setX(((375*larghezza)/1242)/2);
    punti[16].setY(((1617*altezza)/2208)/2);
    punti[17].setX(((894*larghezza)/1242)/2);
    punti[17].setY(((2106*altezza)/2208)/2);
    //land
    punti[18].setX(((925*larghezza)/2208)/2);
    punti[18].setY(((1000*altezza)/1242)/2);
    punti[19].setX(((1280*larghezza)/2208)/2);
    punti[19].setY(((1218*altezza)/1242)/2);
    //punti per il back info
    //dritto
    punti[20].setX(((6*larghezza)/1242)/2);
    punti[20].setY(((72*altezza)/2208)/2);
    punti[21].setX(((264*larghezza)/1242)/2);
    punti[21].setY(((324*altezza)/2208)/2);
    //land
    punti[22].setX(((1785*larghezza)/2208)/2);
    punti[22].setY(((842*altezza)/1242)/2);
    punti[23].setX(((2161*larghezza)/2208)/2);
    punti[23].setY(((1198*altezza)/1242)/2);
    //settings values dritto
    punti[24].setX(((1000*larghezza)/1242)/2);
    punti[24].setY(((630*altezza)/2208)/2);
    punti[25].setX(((1000*larghezza)/1242)/2);
    punti[25].setY(((936*altezza)/2208)/2);
    punti[26].setX(((1000*larghezza)/1242)/2);
    punti[26].setY(((1290*altezza)/2208)/2);
    //settings values land
    punti[27].setX(((1700*larghezza)/2208)/2);
    punti[27].setY(((300*altezza)/1242)/2);
    punti[28].setX(((1700*larghezza)/2208)/2);
    punti[28].setY(((555*altezza)/1242)/2);
    punti[29].setX(((1700*larghezza)/2208)/2);
    punti[29].setY(((870*altezza)/1242)/2);
  } else {
    punti[0].setX((332*larghezza)/1242);
    punti[0].setY((896*altezza)/2208);
    punti[1].setX((888*larghezza)/1242);
    punti[1].setY((1040*altezza)/2208);
    punti[2].setX((364*larghezza)/1242);
    punti[2].setY((1136*altezza)/2208);
    punti[3].setX((848*larghezza)/1242);
    punti[3].setY((1256*altezza)/2208);
    punti[4].setX((444*larghezza)/1242);
    punti[4].setY((1380*altezza)/2208);
    punti[5].setX((768*larghezza)/1242);
    punti[5].setY((1533*altezza)/2208);
    punti[6].setX((468*larghezza)/1242);
    punti[6].setY((1633*altezza)/2208);
    punti[7].setX((801*larghezza)/1242);
    punti[7].setY((1782*altezza)/2208);

    //punti chiave per la seconda foto --> menu landscape
    punti[8].setX((904*larghezza)/2208);
    punti[8].setY((468*altezza)/1242);
    punti[9].setX((1286*larghezza)/2208);
    punti[9].setY((574*altezza)/1242);
    punti[10].setX((918*larghezza)/2208);
    punti[10].setY((600*altezza)/1242);
    punti[11].setX((1286*larghezza)/2208);
    punti[11].setY((700*altezza)/1242);
    punti[12].setX((974*altezza)/1242);
    punti[12].setY((726*larghezza)/2208);
    punti[13].setX((1260*altezza)/1242);
    punti[13].setY((826*larghezza)/2208);
    punti[14].setX((980*altezza)/1242);
    punti[14].setY((986*larghezza)/2208);
    punti[15].setX((1244*altezza)/1242);
    punti[15].setY((1096*larghezza)/2208);
    //punti per il back settings
    //dritto
    punti[16].setX((375*larghezza)/1242);
    punti[16].setY((1617*altezza)/2208);
    punti[17].setX((894*larghezza)/1242);
    punti[17].setY((2106*altezza)/2208);
    //land
    punti[18].setX((925*larghezza)/2208);
    punti[18].setY((1000*altezza)/1242);
    punti[19].setX((1280*larghezza)/2208);
    punti[19].setY((1218*altezza)/1242);
    //punti per il back info
    //dritto
    punti[20].setX((6*larghezza)/1242);
    punti[20].setY((72*altezza)/2208);
    punti[21].setX((264*larghezza)/1242);
    punti[21].setY((324*altezza)/2208);
    //land
    punti[22].setX((1785*larghezza)/2208);
    punti[22].setY((842*altezza)/1242);
    punti[23].setX((2161*larghezza)/2208);
    punti[23].setY((1198*altezza)/1242);
    //settings values dritto
    punti[24].setX((1000*larghezza)/1242);
    punti[24].setY((630*altezza)/2208);
    punti[25].setX((1000*larghezza)/1242);
    punti[25].setY((936*altezza)/2208);
    punti[26].setX((1000*larghezza)/1242);
    punti[26].setY((1290*altezza)/2208);
    //settings values land
    punti[27].setX((1700*larghezza)/2208);
    punti[27].setY((300*altezza)/1242);
    punti[28].setX((1700*larghezza)/2208);
    punti[28].setY((555*altezza)/1242);
    punti[29].setX((1700*larghezza)/2208);
    punti[29].setY((870*altezza)/1242);
  }
}

public void draw() {
  //inizio a disegnare la grafica
  //println(frameRate);

  if (!start_game) {
    player.start();

    if (larghezza>altezza) {  
      image(img2, 0, 0, larghezza, altezza);
      if ((mouseX>=punti[8].getX() && mouseX<=punti[9].getX()) && (mouseY>=punti[8].getY() && mouseY<=punti[9].getY()) && !start_game) {
        start_game=true;
      }

      if ((mouseX>=punti[10].getX() && mouseX<=punti[11].getX()) && (mouseY>=punti[10].getY() && mouseY<=punti[11].getY())) {
        settings=true;
        info=false;
      } 

      if ((mouseX>=punti[12].getX() && mouseX<=punti[13].getX()) && (mouseY>=punti[12].getY() && mouseY<=punti[13].getY())) {
        info=true;
        settings=false;
      }
      if ((mouseX>=punti[14].getX() && mouseX<=punti[15].getX()) && (mouseY>=punti[14].getY() && mouseY<=punti[15].getY())) {
        quit=true;
      }




      //Inizio modalit\u00e0 dritto
    } else {
      image(img, 0, 0, larghezza, altezza);
      if ((mouseX>=punti[0].getX() && mouseX<=punti[1].getX()) && (mouseY>=punti[0].getY() && mouseY<=punti[1].getY()) && !start_game) {
        start_game=true;
      }

      if ((mouseX>=punti[2].getX() && mouseX<=punti[3].getX()) && (mouseY>=punti[2].getY() && mouseY<=punti[3].getY())) {
        settings=true;
        info=false;
        start_game=false;
      } 

      if ((mouseX>=punti[4].getX() && mouseX<=punti[5].getX()) && (mouseY>=punti[4].getY() && mouseY<=punti[5].getY())) {
        info=true;
        settings=false;
        start_game=false;
      }
      if ((mouseX>=punti[6].getX() && mouseX<=punti[7].getX()) && (mouseY>=punti[6].getY() && mouseY<=punti[7].getY())) {
        info=false;
        settings=false;
        quit=true;
      }
    } 


    if (settings) {

      //0->standard  1->verde 2->nero 3->bluu boolean standard = true;boolean green_color = false;boolean black_color=false;boolean blu=false;
      if (larghezza>altezza) {
        //sono land
        //i punti dei toggle sono da 27 a 29
        image(settingl, 0, 0, larghezza, altezza);
        if ((mouseX>=punti[18].getX() && mouseX<=punti[19].getX()) && (mouseY>=punti[18].getY() && mouseY<=punti[19].getY()) && !start_game) {
          settings=false;
        }

        switch(comodo1) {

        default: 
          image(sta, punti[27].getX(), punti[27].getY()-64, 128, 128);
          standard=true;
          green_color=false;
          blu=false;
          black_color=false;
          break;
        case 1: 
          image(ver, punti[27].getX(), punti[27].getY()-64, 128, 128);
          standard=false;
          green_color=true;
          blu=false;
          black_color=false;
          break;
        case 2: 
          image(cel, punti[27].getX(), punti[27].getY()-64, 128, 128);
          standard=false;
          green_color=false;
          blu=true;
          black_color=false;
          break;

        case 3:
          image(ner, punti[27].getX(), punti[27].getY()-64, 128, 128);
          standard=false;
          green_color=false;
          blu=false;
          black_color=true;
          break;
        }

        switch(comodo3) {

        default: 
          image(yes, punti[28].getX(), punti[28].getY()-64, 128, 128);
          sound_control=true;
          player.setVolume(1.0f, 1.0f);
          break;
        case 1: 
          image(no, punti[28].getX(), punti[28].getY()-64, 128, 128);
          sound_control=false;
          player.setVolume(0.0f, 0.0f);
          break;
        }
      } else {
        //sono dritto
        image(setting, 0, 0, larghezza, altezza);
        switch(comodo) {

        default: 
          image(sta, punti[24].getX(), punti[24].getY()-64, 128, 128);
          standard=true;
          green_color=false;
          blu=false;
          black_color=false;
          break;
        case 1: 
          image(ver, punti[24].getX(), punti[24].getY()-64, 128, 128);
          standard=false;
          green_color=true;
          blu=false;
          black_color=false;
          break;
        case 2: 
          image(cel, punti[24].getX(), punti[24].getY()-64, 128, 128);
          standard=false;
          green_color=false;
          blu=true;
          black_color=false;
          break;

        case 3:
          image(ner, punti[24].getX(), punti[24].getY()-64, 128, 128);
          standard=false;
          green_color=false;
          blu=false;
          black_color=true;
          break;
        }

        switch(comodo2) {

        default: 
          image(yes, punti[25].getX(), punti[25].getY()-64, 128, 128);
          sound_control=true;
          player.setVolume(1.0f, 1.0f);
          break;
        case 1: 
          image(no, punti[25].getX(), punti[25].getY()-64, 128, 128);
          sound_control=false;
          player.setVolume(0.0f, 0.0f);
          break;
        }




        if ((mouseX>=punti[16].getX() && mouseX<=punti[17].getX()) && (mouseY>=punti[16].getY() && mouseY<=punti[17].getY()) && !start_game) {
          settings=false;
        }
      }
      //fine start game
    }

    if (info) {
      if (larghezza>altezza) {
        //sono land
        image(infosl, 0, 0, larghezza, altezza);
        if ((mouseX>=punti[13].getX() && mouseX<=punti[14].getX()) && (mouseY>=punti[13].getY() && mouseY<=punti[14].getY()) && !start_game) {
          info=false;
        }
      } else {
        //sono dritto
        image(infos, 0, 0, larghezza, altezza);
        if ((mouseX>=punti[20].getX() && mouseX<=punti[21].getX()) && (mouseY>=punti[20].getY() && mouseY<=punti[21].getY()) && !start_game) {
          info=false;
        }
      }
    }

    if (quit) {
      //finish();
      System.exit(0);
    }
  }






  if (start_game==true) {
    if (com==true) {
      image(img3, 0, 0, larghezza, altezza);
      init();
      Start();
      com=false;
    }
  }
}



public void mousePressed() {
  Ymouse=mouseY;
  Xmouse=mouseX;
}

public void mouseReleased() {  
  if (start_game) {

    if (mouseY>Ymouse+c1) {
      for (int i=0; i<4; i++) {
        for (int j=0; j<4; j++) {
          w[i][j].value = griglia[i][j].value;
        }
      }
      moveDown();
      for (int i=0; i<4; i++) {
        for (int j=0; j<4; j++) {
          if (griglia[i][j].value != w[i][j].value) {
            cambiamento = true;
          }
        }
      }
      if (cambiamento==true) {
        randomSpawn();
        cambiamento=false;
      }

      if (checkLose()) {
        image(perdita, larghezza/8, altezza*0.4f, larghezza/1.5f, altezza/2.75f);
        player2.start();
      }
    }




    if (mouseY<Ymouse-c1) {
      for (int i=0; i<4; i++) {
        for (int j=0; j<4; j++) {
          w[i][j].value = griglia[i][j].value;
        }
      }
      moveUp();
      for (int i=0; i<4; i++) {
        for (int j=0; j<4; j++) {
          if (griglia[i][j].value != w[i][j].value) {
            cambiamento = true;
          }
        }
      }
      if (cambiamento==true) {
        randomSpawn();
        cambiamento=false;
      }

      if (checkLose()) {
        image(perdita, larghezza/8, altezza*0.4f, larghezza/1.5f, altezza/2.75f);
        player2.start();
      }
    }
    if (mouseX<Xmouse-c1) {
      for (int i=0; i<4; i++) {
        for (int j=0; j<4; j++) {
          w[i][j].value = griglia[i][j].value;
        }
      }
      moveLeft();
      for (int i=0; i<4; i++) {
        for (int j=0; j<4; j++) {
          if (griglia[i][j].value != w[i][j].value) {
            cambiamento = true;
          }
        }
      }
      if (cambiamento==true) {
        randomSpawn();
        cambiamento=false;
      }

      if (checkLose()) {
        fill(255, 0, 0);
        image(perdita, larghezza/8, altezza*0.4f, larghezza/1.5f, altezza/2.75f);
        fill(0, 0, 0);
        textSize(50);
      }
    }
    if (mouseX>Xmouse+c1) {
      for (int i=0; i<4; i++) {
        for (int j=0; j<4; j++) {
          w[i][j].value = griglia[i][j].value;
        }
      }
      moveRight();
      for (int i=0; i<4; i++) {
        for (int j=0; j<4; j++) {
          if (griglia[i][j].value != w[i][j].value) {
            cambiamento = true;
          }
        }
      }
      if (cambiamento==true) {
        randomSpawn();
        cambiamento=false;
      }

      if (checkLose()) {
        image(perdita, larghezza/8, altezza*0.4f, larghezza/1.5f, altezza/2.75f);
        player2.start();
      }
    }
  }

  if (settings) {

    //print(standard +""+ green_color +""+ blu +""+ black_color);

    if (mouseX>=punti[24].getX() && mouseX<=punti[24].getX()+128 && mouseY>=punti[24].getY()-64 && mouseY<=punti[24].getY()+128 && !start_game) {
      comodo++;
      player1.start();
      if (comodo>3) comodo=0;
    }

    if (mouseX>=punti[27].getX() && mouseX<=punti[27].getX()+128 && mouseY>=punti[27].getY()-64 && mouseY<=punti[27].getY()+128 && !start_game) {
      comodo1++;
      player1.start();
      if (comodo1>3) comodo1=0;
    }

    if (mouseX>=punti[25].getX() && mouseX<=punti[25].getX()+128 && mouseY>=punti[25].getY()-64 && mouseY<=punti[25].getY()+128 && !start_game) {
      comodo2++;
      player1.start();
      if (comodo2>1) comodo2=0;
    }


    if (mouseX>=punti[28].getX() && mouseX<=punti[28].getX()+128 && mouseY>=punti[28].getY()-64 && mouseY<=punti[28].getY()+128 && !start_game) {
      comodo3++;
      player1.start();
      if (comodo3>1) comodo3=0;
    }
  }
}





public void checkXY(Quadrato q) {

  for (int i=0; i<4; i++) {
    for (int j=0; j<4; j++) {
      if (griglia[i][j].x==q.x && griglia[i][j].y==q.y) {
        riga = i;
        colonna = j;
        //break;
      }
    }
  }
}

public void drawShape(Quadrato[][] a) {  
  for (int i=0; i<4; i++) {
    for (int j=0; j<4; j++) {
      disegna(a[i][j]);
    }
  }
}


public void init() {

  float c5 =  width/4;

  fill(187, 173, 170);  
  rect(0, 2*c2-15, 6*c3, 4*c4, 10);
  fill(205, 193, 180);

  //prima riga
  griglia[0][0] = new Quadrato(10, 2*c2-5, c5-10, c4-10, standard_color, false, 0);
  griglia[0][1] = new Quadrato(c5+10, 2*c2-5, c5-10, c4-10, standard_color, false, 0);
  griglia[0][2] = new Quadrato(2*c5+10, 2*c2-5, c5-10, c4-10, standard_color, false, 0);
  griglia[0][3] = new Quadrato(3*c5+10, 2*c2-5, c5-10, c4-10, standard_color, false, 0);
  //seconda riga
  griglia[1][0] = new Quadrato(10, 3*c2-5, c5-10, c4-10, standard_color, false, 0);
  griglia[1][1] = new Quadrato(c5+10, 3*c2-5, c5-10, c4-10, standard_color, false, 0);
  griglia[1][2] = new Quadrato(2*c5+10, 3*c2-5, c5-10, c4-10, standard_color, false, 0);
  griglia[1][3] = new Quadrato(3*c5+10, 3*c2-5, c5-10, c4-10, standard_color, false, 0);
  //terza riga
  griglia[2][0] = new Quadrato(10, 4*c2-5, c5-10, c4-10, standard_color, false, 0);
  griglia[2][1] = new Quadrato(c5+10, 4*c2-5, c5-10, c4-10, standard_color, false, 0);
  griglia[2][2] = new Quadrato(2*c5+10, 4*c2-5, c5-10, c4-10, standard_color, false, 0);
  griglia[2][3] = new Quadrato(3*c5+10, 4*c2-5, c5-10, c4-10, standard_color, false, 0);
  //quarta riga
  griglia[3][0] = new Quadrato(10, 5*c2-5, c5-10, c4-10, standard_color, false, 0);
  griglia[3][1] = new Quadrato(c5+10, 5*c2-5, c5-10, c4-10, standard_color, false, 0);
  griglia[3][2] = new Quadrato(2*c5+10, 5*c2-5, c5-10, c4-10, standard_color, false, 0);
  griglia[3][3] = new Quadrato(3*c5+10, 5*c2-5, c5-10, c4-10, standard_color, false, 0);


  // INIZIALIZZO GRIGLIA DI COMODO :

  w[0][0] = new Quadrato(c1, c2+50, c3-10, c4-10, standard_color, false, 0);
  w[0][1] = new Quadrato(2*c1, c2+50, c3-10, c4-10, standard_color, false, 0);
  w[0][2] = new Quadrato(3*c1, c2+50, c3-10, c4-10, standard_color, false, 0);
  w[0][3] = new Quadrato(4*c1, c2+50, c3-10, c4-10, standard_color, false, 0);
  //seconda riga
  w[1][0] = new Quadrato(c1, 2*c2+50, c3-10, c4-10, standard_color, false, 0);
  w[1][1] = new Quadrato(2*c1, 2*c2+50, c3-10, c4-10, standard_color, false, 0);
  w[1][2] = new Quadrato(3*c1, 2*c2+50, c3-10, c4-10, standard_color, false, 0);
  w[1][3] = new Quadrato(4*c1, 2*c2+50, c3-10, c4-10, standard_color, false, 0);
  //terza riga
  w[2][0] = new Quadrato(c1, 3*c2+50, c3-10, c4-10, standard_color, false, 0);
  w[2][1] = new Quadrato(2*c1, 3*c2+50, c3-10, c4-10, standard_color, false, 0);
  w[2][2] = new Quadrato(3*c1, 3*c2+50, c3-10, c4-10, standard_color, false, 0);
  w[2][3] = new Quadrato(4*c1, 3*c2+50, c3-10, c4-10, standard_color, false, 0);
  //quarta riga
  w[3][0] = new Quadrato(c1, 4*c2+50, c3-10, c4-10, standard_color, false, 0);
  w[3][1] = new Quadrato(2*c1, 4*c2+50, c3-10, c4-10, standard_color, false, 0);
  w[3][2] = new Quadrato(3*c1, 4*c2+50, c3-10, c4-10, standard_color, false, 0);
  w[3][3] = new Quadrato(4*c1, 4*c2+50, c3-10, c4-10, standard_color, false, 0);




  score = new Quadrato(0.80f*4.5f*c1+30, 0.60f*c2-60, 2*c3, 0.75f*c4, standard_color, false, 0);
  disegna(score);
  fill(0);
  text("Score :", 5*c1+30-65, c2-55+(0.5f*((0.4f)*c4+15)));
  drawShape(griglia);
}

public void disegna(Quadrato q) {
  float co1=0;
  float co2=0;
  fill(standard_color);
  if (q.c !=standard_color || q.value!=0) {
    if (standard==true) {
      switch(q.value) {

      case 1 : 
        fill(k[0]);
        break;
      case 2 : 
        fill(k[1]);
        break;
      case 3 : 
        fill(k[2]);
        break;
      case 5 : 
        fill(k[3]);
        break;
      case 8 : 
        fill(k[4]);
        break;
      case 13 : 
        fill(k[5]);
        break;
      case 21 : 
        fill(k[6]);
        break;
      case 34 : 
        fill(k[7]);
        break;
      case 55 : 
        fill(k[8]);
        break;
      case 89 : 
        fill(k[9]);
        break;
      case 144 : 
        fill(k[10]);
        break;
      case 233 : 
        fill(k[11]);
        break;
      case 377 : 
        fill(k[12]);
        break;
      case 610 : 
        fill(k[13]);
        break;
      case 987 : 
        fill(k[14]);
        break;
      case 1597 : 
        fill(k[15]);
        break;
      case 2584 : 
        fill(k[16]);
        break;
      }
    } else if (green_color==true) {
      switch(q.value) {

      case 1 : 
        fill(green[0]);
        break;
      case 2 : 
        fill(green[1]);
        break;
      case 3 : 
        fill(green[2]);
        break;
      case 5 : 
        fill(green[3]);
        break;
      case 8 : 
        fill(green[4]);
        break;
      case 13 : 
        fill(green[5]);
        break;
      case 21 : 
        fill(green[6]);
        break;
      case 34 : 
        fill(green[7]);
        break;
      case 55 : 
        fill(green[8]);
        break;
      case 89 : 
        fill(green[9]);
        break;
      case 144 : 
        fill(green[10]);
        break;
      case 233 : 
        fill(green[11]);
        break;
      case 377 : 
        fill(green[12]);
        break;
      case 610 : 
        fill(green[13]);
        break;
      case 987 : 
        fill(green[14]);
        break;
      case 1597 : 
        fill(green[15]);
        break;
      case 2584 : 
        fill(green[16]);
        break;
      }
    } else if (blu==true) {
      switch(q.value) {

      case 1 : 
        fill(bluu[0]);
        break;
      case 2 : 
        fill(bluu[1]);
        break;
      case 3 : 
        fill(bluu[2]);
        break;
      case 5 : 
        fill(bluu[3]);
        break;
      case 8 : 
        fill(bluu[4]);
        break;
      case 13 : 
        fill(bluu[5]);
        break;
      case 21 : 
        fill(bluu[6]);
        break;
      case 34 : 
        fill(bluu[7]);
        break;
      case 55 : 
        fill(bluu[8]);
        break;
      case 89 : 
        fill(bluu[9]);
        break;
      case 144 : 
        fill(bluu[10]);
        break;
      case 233 : 
        fill(bluu[11]);
        break;
      case 377 : 
        fill(bluu[12]);
        break;
      case 610 : 
        fill(bluu[13]);
        break;
      case 987 : 
        fill(bluu[14]);
        break;
      case 1597 : 
        fill(bluu[15]);
        break;
      case 2584 : 
        fill(bluu[16]);
        break;
      }
    } else if (black_color==true) {
      switch(q.value) {

      case 1 : 
        fill(black[0]);
        break;
      case 2 : 
        fill(black[1]);
        break;
      case 3 : 
        fill(black[2]);
        break;
      case 5 : 
        fill(black[3]);
        break;
      case 8 : 
        fill(black[4]);
        break;
      case 13 : 
        fill(black[5]);
        break;
      case 21 : 
        fill(black[6]);
        break;
      case 34 : 
        fill(black[7]);
        break;
      case 55 : 
        fill(black[8]);
        break;
      case 89 : 
        fill(black[9]);
        break;
      case 144 : 
        fill(black[10]);
        break;
      case 233 : 
        fill(black[11]);
        break;
      case 377 : 
        fill(black[12]);
        break;
      case 610 : 
        fill(black[13]);
        break;
      case 987 : 
        fill(black[14]);
        break;
      case 1597 : 
        fill(black[15]);
        break;
      case 2584 : 
        fill(black[16]);
        break;
      }
    } else {
      fill(205, 193, 180);
    }


    checkXY(q);
    rect(q.x, q.y, q.Width-10, q.Height-10, 10);
    fill(0);

    if (q.value<=8) {
      co1 = 1;  
      co2 = 2.1f;
    } else if (q.value > 8 && q.value<100) {
      co1 = 0.75f; 
      co2 = 1.75f;
    } else if (q.value >=100 && q.value<1000) {
      co1 = 0.50f; 
      co2 = 1.20f;
    }
    textSize(textsize);
    text(q.value, q.x+(q.Width/2)-(textsize/co2), q.y+(q.Height/2));
  } else {
    fill(standard_color);
    rect(q.x, q.y, q.Width-10, q.Height-10, 10);
    q.assegnato=false;
  }
}


public void randomSpawn() {
  int r=0;
  int c=0;
  int fato=0;
  int valore=0;
  int r1=0; 
  int c1=0;
  do {
    r=(int)random(4);
    c=(int)random(4);
  } while (griglia[r][c].assegnato==true);




  fato=(int)random(100);
  if (fato < 40) {
    valore=1;
  } else if (fato >=40 && fato<92) {
    valore=2;
  } else {
    valore=3;
  }

  griglia[r][c].assegnato=true;
  griglia[r][c].value=valore;    
  if (!midFull()) {
    do {
      r1=(int)random(4);
      c1=(int)random(4);
    } while (griglia[r1][c1].assegnato==true); 

    griglia[r1][c1].assegnato=true;
    griglia[r1][c1].value=valore;
    disegna(griglia[r1][c1]);
  }  

  disegna(griglia[r][c]);
}



public boolean midFull() {
  int cont=0;
  for (int i=0; i<griglia.length; i++) {
    for (int j=0; j<griglia.length; j++) {
      if (griglia[i][j].assegnato==true)
        cont++;
    }
  }

  if (cont>15) {
    return true;
  } else {
    return false;
  }
}


public void Start() {
  int r=0;
  int c=0;
  int r1=0; 
  int c1=0;
  int r2=0;
  int c2=0;
  esegui=true;
  //print(esegui);
  if (esegui==true) {
    do {
      r=(int)random(4);
      c=(int)random(4);
    } while ((griglia[r][c].assegnato==true));

    do {
      r1=(int)random(4);
      c1=(int)random(4);
    } while ((griglia[r1][c1].assegnato==true));

    do {
      r2=(int)random(4);
      c2=(int)random(4);
    } while ((griglia[r2][c2].assegnato==true));

    // print("  "+r+"  "+"  "+c+"  "+"  "+r1+"  "+c1+"  "+"  "+r2+"  "+"  "+c2+"  ");
  }
  esegui=false;


  griglia[r][c].assegnato=true;
  griglia[r][c].value=1;
  griglia[r][c].c=k[1];
  griglia[r1][c1].assegnato=true;
  griglia[r1][c1].value=2;
  griglia[r1][c1].c=k[2];
  griglia[r2][c2].assegnato=true;
  griglia[r2][c2].value=2;
  griglia[r2][c2].c=k[2];
  disegna(griglia[r][c]);
  disegna(griglia[r1][c1]);
  disegna(griglia[r2][c2]);
  //drawShape(griglia);
}

public void reset(Quadrato[] q) {
  for (int n=0; n<q.length; n++) {
    q[n]=new Quadrato(c1, c2, c3-10, c4-10, standard_color, false, 0);
  }
}


public void moveDown() {
  int d, e, punti=0;
  Quadrato tmp[] = new Quadrato[4];
  //movimento dall'alto verso il basso 
  //vedo se ci sono quadrati adiacenti nella direzione down->top se si faccio merge e shifto in alto
  for (int i=3; i>0; i--) {
    for (int j=0; j<4; j++) {
      reset(tmp);
      if (griglia[i][j].assegnato==true ) {
        if (isFibonacci(griglia[i][j].value, griglia[i-1][j].value)==true) {
          punti=griglia[i][j].value+griglia[i-1][j].value;
          score.setValue(score.getValue()+punti);
          disegna(score);
          griglia[i][j].value+=griglia[i-1][j].value;
          griglia[i-1][j].c = standard_color;
          griglia[i-1][j].value = 0;
          griglia[i-1][j].assegnato = false;
          disegna(griglia[i][j]);
          disegna(griglia[i-1][j]);
        }
      } else {
        //shifto in basso tutto il vettore colonna
        //metodo 1
        d=0;
        for (int a=i; a>=0; a--) {
          if (griglia[a][j].assegnato==true) {
            tmp[d].value=griglia[a][j].value;
            tmp[d].c=griglia[a][j].c;
            tmp[d].assegnato=griglia[a][j].assegnato;
            d++;
          }
        }

        e=0;
        for (int k=i; k>=0; k--) {
          //shift verso l'alto
          griglia[k][j].value=tmp[e].value;
          griglia[k][j].c=tmp[e].c;
          griglia[k][j].assegnato=tmp[e].assegnato;
          disegna(griglia[k][j]);
          e++;
        }
      }
      drawShape(griglia);
    }
  }
}



public void moveUp() {
  int d, e, punti=0;
  Quadrato tmp[] = new Quadrato[4];
  //movimento dall'alto verso il basso 
  //vedo se ci sono quadrati adiacenti nella direzione down->top se si faccio merge e shifto in alto
  for (int i=0; i<3; i++) {
    for (int j=0; j<4; j++) {
      reset(tmp);

      if (griglia[i][j].assegnato==true) {
        if (isFibonacci(griglia[i][j].value, griglia[i+1][j].value)==true) {
          punti=griglia[i][j].value+griglia[i+1][j].value;
          score.setValue(score.getValue()+punti);
          disegna(score);
          griglia[i][j].value+=griglia[i+1][j].value;
          griglia[i+1][j].c = standard_color;
          griglia[i+1][j].value = 0;
          griglia[i+1][j].assegnato = false;
          disegna(griglia[i][j]);
          disegna(griglia[i+1][j]);
        }
      } else {
        //shifto in basso tutto il vettore colonna
        //metodo 1
        d=0;
        for (int a=i; a<4; a++) {
          if (griglia[a][j].assegnato==true) {
            tmp[d].value=griglia[a][j].value;
            tmp[d].c=griglia[a][j].c;
            tmp[d].assegnato=griglia[a][j].assegnato;
            d++;
          }
        }

        e=0;
        for (int k=i; k<4; k++) {
          //shift verso l'alto
          griglia[k][j].value=tmp[e].value;
          griglia[k][j].c=tmp[e].c;
          griglia[k][j].assegnato=tmp[e].assegnato;
          disegna(griglia[k][j]);
          e++;
        }
      }
      drawShape(griglia);
    }
  }
}


public void moveRight() {
  Quadrato tmp[] = new Quadrato[4];
  int d, e, punti=0;
  //movimento dall'alto verso il basso 
  //vedo se ci sono quadrati adiacenti nella direzione down->top se si faccio merge e shifto in alto
  for (int i=0; i<4; i++) {
    for (int j=3; j>0; j--) {
      reset(tmp);

      if (griglia[i][j].assegnato==true) {
        if (isFibonacci(griglia[i][j].value, griglia[i][j-1].value)==true) {
          punti=griglia[i][j].value+griglia[i][j-1].value;
          score.setValue(score.getValue()+punti);
          disegna(score);
          griglia[i][j].value+=griglia[i][j-1].value;
          griglia[i][j-1].c = standard_color;
          griglia[i][j-1].value = 0;
          griglia[i][j-1].assegnato = false;
          disegna(griglia[i][j]);
          disegna(griglia[i][j-1]);
        }
      } else {
        d=0;
        for (int a=j; a>=0; a--) {
          if (griglia[i][a].assegnato==true) {
            tmp[d].value=griglia[i][a].value;
            tmp[d].c=griglia[i][a].c;
            tmp[d].assegnato=griglia[i][a].assegnato;
            d++;
          }
        }

        e=0;
        for (int k=j; k>=0; k--) {
          //shift verso l'alto
          griglia[i][k].value=tmp[e].value;
          griglia[i][k].c=tmp[e].c;
          griglia[i][k].assegnato=tmp[e].assegnato;
          disegna(griglia[i][k]);
          e++;
        }
      }
      drawShape(griglia);
    }
  }
}


public void moveLeft() {
  int d, e, punti=0;
  Quadrato tmp[] = new Quadrato[4];
  //movimento dall'alto verso il basso 
  //vedo se ci sono quadrati adiacenti nella direzione down->top se si faccio merge e shifto in alto
  for (int i=0; i<4; i++) {
    for (int j=0; j<3; j++) {
      reset(tmp);

      if (griglia[i][j].assegnato==true) {
        if (isFibonacci(griglia[i][j].value, griglia[i][j+1].value)==true) {
          punti=griglia[i][j].value+griglia[i][j+1].value;
          score.setValue(score.getValue()+punti);
          disegna(score);
          griglia[i][j].value+=griglia[i][j+1].value;
          griglia[i][j+1].c = standard_color;
          griglia[i][j+1].value = 0;
          griglia[i][j+1].assegnato = false;
          disegna(griglia[i][j]);
          disegna(griglia[i][j+1]);
        }
      } else {
        d=0;
        for (int a=j; a<4; a++) {
          if (griglia[i][a].assegnato==true) {
            tmp[d].value=griglia[i][a].value;
            tmp[d].c=griglia[i][a].c;
            tmp[d].assegnato=griglia[i][a].assegnato;
            d++;
          }
        }

        e=0;
        for (int k=j; k<4; k++) {
          //shift verso l'alto
          griglia[i][k].value=tmp[e].value;
          griglia[i][k].c=tmp[e].c;
          griglia[i][k].assegnato=tmp[e].assegnato;
          disegna(griglia[i][k]);
          e++;
        }
      }
      drawShape(griglia);
    }
  }
}


public boolean isFibonacci(int a, int c) {
  boolean b=false;
  for (int i=0; i<values.length; i++) {
    if (values[i]==(a+c)) {
      return !b;
    }
  }
  return b;
}

public boolean checkLose() {
  int a = 0;
  boolean c = true; //mi indica se posso ancora muovermi verso il basso
  boolean d = true;
  boolean e = true;
  boolean f= true;
  boolean g = true;
  for (int i=0; i<griglia.length; i++) {
    for (int j=0; j<griglia.length; j++) {
      if (griglia[i][j].assegnato) a++;
    }
  }
  if (a==16) { 

    //down
    for (int i=3; i>0; i--) {
      for (int j=0; j<4; j++) {
        if (isFibonacci(griglia[i][j].value, griglia[i-1][j].value)==true) {
          c=false;
          break;
        }
      }
    }
    //up
    for (int i=0; i<3; i++) {
      for (int j=0; j<4; j++) {
        if (isFibonacci(griglia[i][j].value, griglia[i+1][j].value)==true) {
          d=false;        
          break;
        }
      }
    }
    //left
    for (int i=0; i<4; i++) {
      for (int j=0; j<3; j++) {
        if (isFibonacci(griglia[i][j].value, griglia[i][j+1].value)==true) {
          e=false;
          break;
        }
      }
    }
    //right
    for (int i=0; i<4; i++) {
      for (int j=3; j>0; j--) {
        if (isFibonacci(griglia[i][j].value, griglia[i][j-1].value)==true) {
          f=false;
          break;
        }
      }
    }

    g = (c && d && e && f) ;
  } else {  
    g = false;
  }
  return g;
}
class Point{

private float x;
private float y;

Point(){

}

Point(float x,float y){
this.x=x;
this.y=y;  
}

public void setX(float x) {
this.x=x;
}

public float getX(){
return x;
}

public void setY(float y) {
this.y=y;
}
public float getY(){
return y;
}





}


public class Quadrato {

  private float x;
  private float y;
  private float Width;
  private float Height;
  private int c;
  private int value;
  private boolean assegnato;
  

  Quadrato() {
  }


  Quadrato(float x, float y, float Width, float Height, int c, boolean assegnato, int value) {
    this.x=x;
    this.y=y;
    this.Width=Width;
    this.Height=Height;
    this.c=c;
    this.assegnato=assegnato;
    this.value=value;
  }

  public void finalize() {
  }
  
  public void setValue(int valore){
  this.value=valore;  
  }
  
   public int getValue(){
  return this.value;  
  }  
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "G1597" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
