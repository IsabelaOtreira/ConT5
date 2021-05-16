using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace WindowsFormsApplication1
{
    public abstract class Actor
    {
        protected int x, y, w, h, SpeedX, SpeedY;
        protected Image Texture;
        protected Pen pen;

        protected void Create(int X, int Y, int W, int H)
        {
            this.x = X;
            this.y = Y;
            this.w = W;
            this.h = H;
        }

        public int getX
        {
            get { return x; }
            set { x = value; }
        }

        public int getY
        {
            get { return y;}
            set { y = value; }
        }

        public int getW
        {
            get { return w;}
            set { w = value; }
        }

        public int getH
        {
            get { return h; }
            set { h = value; }
        }

        public abstract void Draw(Graphics G);
        public abstract void Update();
    }
}
