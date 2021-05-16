using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Media;
using System.Windows.Forms;
using System.IO;

namespace WindowsFormsApplication1
{
    public partial class Form1 : Form
    {
        QuadTree quadtree;
        RectPoint range;
        List<RectPoint> found;

        public static int newWidth, newHeight;

        public Form1()
        {
            #region initializeForm
            InitializeComponent();
            this.Width = 800;
            this.Height = 600;
            newWidth = this.Width;
            newHeight = this.Height;
            this.CenterToScreen();

            SetStyle(ControlStyles.UserPaint, true);
            SetStyle(ControlStyles.AllPaintingInWmPaint, true);
            SetStyle(ControlStyles.OptimizedDoubleBuffer, true);

            Timer timer = new Timer();
            timer.Interval = 1;
            timer.Start();
            timer.Tick += new EventHandler(UpdateGame);

            Paint += new PaintEventHandler(DrawGame);
            #endregion

            range = new RectPoint(0, 0, 250, 250, Brushes.Green, Brushes.Green);
            found = new List<RectPoint>();

            quadtree = new QuadTree(4);

        }

        void DrawGame(object sender, PaintEventArgs Draw)
        {
            Draw.Graphics.Clear(Color.Black);
            found.Clear();
            quadtree.Draw(Draw.Graphics);

            range.Draw(Draw.Graphics);

            quadtree.Query(range, found);

            foreach (RectPoint p in found)
            {
                p.ChangePen(Brushes.Green);
            }

            Draw.Graphics.DrawString("Quadtree calcs: " + found.Count * found.Count, System.Drawing.SystemFonts.DefaultFont, Brushes.Blue, 25, 50 );
            Draw.Graphics.DrawString("Normal calcs: " + quadtree.allPointsCount * quadtree.allPointsCount, System.Drawing.SystemFonts.DefaultFont, Brushes.Blue, 25, 100);
            Draw.Graphics.DrawString("speedUp: " + ((quadtree.allPointsCount * quadtree.allPointsCount) - (found.Count * found.Count)), System.Drawing.SystemFonts.DefaultFont, Brushes.Blue, 25, 150);

        }

        void UpdateGame(object sender, EventArgs e)
        {
            quadtree.Update();
            Invalidate();
        }

        private void Input(object sender, KeyEventArgs e)
        {

        }

        private void InputUp(object sender, KeyEventArgs e)
        {
            if(e.KeyCode == Keys.Escape)
            {
                Application.Exit();
            }
        }

        private void Click(object sender, MouseEventArgs e)
        {
            quadtree.Insert(new RectPoint(e.X, e.Y, 10, 10, Brushes.Red, Brushes.Red));
        }

        private void MouseMovement(object sender, MouseEventArgs e)
        {
            range.getX = e.X;
            range.getY = e.Y;
        }
    }
}
