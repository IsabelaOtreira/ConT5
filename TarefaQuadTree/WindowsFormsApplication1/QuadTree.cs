using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;

namespace WindowsFormsApplication1
{
    class QuadTree
    {

        List<RectPoint> gPoints;
        Node ogNode;
        int capacity;

        public int allPointsCount
        {
            get { return gPoints.Count; }
        }

        public QuadTree(int capacity)
        {
            gPoints = new List<RectPoint>();
            this.capacity = capacity;
            ogNode = new Node(1, 1, Form1.newWidth, Form1.newHeight, Brushes.Black, Brushes.White, null, capacity);
        }

        void Build()
        {
            ogNode = new Node(1, 1, Form1.newWidth, Form1.newHeight, Brushes.Black, Brushes.White, null, capacity);

            foreach(RectPoint p in gPoints)
            {
                ogNode.Insert(p);
            }
        }

        public List<RectPoint> Query(RectPoint range, List<RectPoint> found)
        {
            return ogNode.Query(range, found);
        }

        public void Insert(RectPoint point)
        {
            gPoints.Add(point);
        }

        public List<RectPoint> getGPoints
        {
            get { return getGPoints; }
        }

        public void Draw(Graphics g)
        {
            ogNode.Draw(g);

            foreach (RectPoint point in gPoints)
            {
                point.Draw(g);
                point.ChangePen(Brushes.Red);
            }
        }

        public void Update()
        {
            foreach (RectPoint point in gPoints)
            {
                point.Update();
            }

            Build();
        }

    }
}
