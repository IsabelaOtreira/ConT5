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
    public class RectPoint : Actor
    {
        Random randomizer;
        int[] direction;
        protected Brush fill, stroke;

        public RectPoint(int x, int y, int w, int h, Brush fill, Brush stroke)
        {
            Create(x, y, w, h);
            randomizer = new Random();
            direction = new int[] { 1, -1 };

            SpeedX = (randomizer.Next(3) + 1) * direction[randomizer.Next(1)];
            SpeedY = (randomizer.Next(3) + 1) * direction[randomizer.Next(1)];
            this.fill = fill;
            this.stroke = stroke;
            pen = new Pen(this.stroke);
        }

        public override void Draw(Graphics G)
        {
            G.DrawRectangle(pen, x,y,w,h);

            //G.FillRectPoint(fill, x, y, w, h);
        }

        public void ChangePen(Brush color)
        {
            pen = new Pen(color);
        }

        public bool Contain(RectPoint rect)
        {
            return (this.x < rect.x + rect.w && 
                this.x + this.w > rect.x &&
                this.y < rect.y + rect.h &&
                this.y + this.h > rect.y);
        }

        public bool Intersects(RectPoint range)
        {
            return !(range.x - range.w > this.x + this.w ||
                range.x + range.w < this.x - this.w ||
                range.y - range.h > this.y + this.h ||
                range.y + range.h < this.y - this.h);
        }

        public override void Update()
        {
            x += SpeedX;
            y += SpeedY;

            if (x > 800 - w || x < 2) { SpeedX *= -1; }
            if (y > 600 - h || y < 2) { SpeedY *= -1; }
        }
    }
}
