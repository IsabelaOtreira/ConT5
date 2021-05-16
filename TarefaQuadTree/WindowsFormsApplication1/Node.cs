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
    class Node : RectPoint
    {
        Node parent, tR, tL, bR, bL;
        List<RectPoint> points;
        int capacity,  subdivisionV, subDPosV;
        bool divided;

        public override void Draw(Graphics G)
        {
            G.DrawRectangle(pen, x, y, w, h);
            if (divided)
            {
                tR.Draw(G);
                tL.Draw(G);
                bR.Draw(G);
                bL.Draw(G);
            }
        }

        public Node(int x, int y, int w, int h, Brush fill, Brush stroke, Node parent, int capacity) : base(x, y, w, h, fill, stroke)
        {
            this.parent = parent;
            this.capacity = capacity;
            subdivisionV = 2;
            subDPosV = 2;
            points = new List<RectPoint>();
        }

        void passToChildren(Node child)
        {
            foreach (RectPoint p in points)
            {
                child.Insert(p);
            }
        }

        public bool Insert(RectPoint point)
        {
            if (!this.Contain(point))
            {
                return false;
            }

            if (points.Count < capacity && !divided)
            {
                points.Add(point);
                return true;
            }
            else
            {
                if (!divided)
                {
                    Subdivide();
                    passToChildren(tR);
                    passToChildren(tL);
                    passToChildren(bR);
                    passToChildren(bL);
                    points.Clear();
                }

                tR.Insert(point);

                tL.Insert(point);

                bR.Insert(point);

                bL.Insert(point);

                return false;
            }
        }

        void Subdivide()
        {
            tR = new Node(this.getX + this.getW / subDPosV, this.getY + 1, this.getW / subdivisionV, this.getH / subdivisionV, Brushes.Black, Brushes.White, this, this.capacity);

            tL = new Node(this.getX + 1, this.getY + 1, this.getW / subdivisionV, this.getH / subdivisionV, Brushes.Black, Brushes.White, this, this.capacity);

            bR = new Node(this.getX + this.getW / subDPosV, this.getY + this.getH / subDPosV, this.getW / subdivisionV, this.getH / subdivisionV, Brushes.Black, Brushes.White, this, this.capacity);

            bL = new Node(this.getX + 1, this.getY + this.getH / subDPosV, this.getW / subdivisionV, this.getH / subdivisionV, Brushes.Black, Brushes.White, this, this.capacity);

            divided = true;
        }

        public List<RectPoint> Query(RectPoint range, List<RectPoint> found)
        {
            if (found == null)
            {
                found = new List<RectPoint>();
            }

            if (!this.Intersects(range))
            {
                return found;
            }
            else
            {
                foreach (RectPoint point in points)
                {
                    if (range.Contain(point))
                    {
                        found.Add(point);
                    }
                }

                if (divided)
                {
                    tR.Query(range, found);
                    tL.Query(range, found);
                    bR.Query(range, found);
                    bL.Query(range, found);
                }

                return found;
            }
        }

    }
}
