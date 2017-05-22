package cn.zy;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends Frame implements KeyListener {
	private JLabel wolf_jb;// 狼
	JLabel[][] sheeps = new JLabel[12][16];// 用于羊的移动
	private int wx;// 灰太狼在水平方向的位置
	private int wy;// 灰太狼在竖直方向的位置
	int num = 0;// 表示初始
	int total = 3;// 表示总共的箱子数目
	private int[][] tree = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1 }, 
			{ 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	// 构造方法
	public MainFrame() {
		house_init();
		wolf_init();
		sheep_init();
		tree_init();
		backGroundInit();
		setMainFrame();
		this.addKeyListener(this);// 事件监听,表示对当前窗体的监听
		// 窗体监听，用于关闭该窗体
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// 设置窗体大小不可改变
		this.setResizable(false);
		// 改变窗体图标
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image image = kit.createImage("src/title.gif");
		setIconImage(image);
	}

	// 添加障碍
	private void tree_init() {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 12; j++) {
				if (tree[j][i] == 1) {
					Icon icon = new ImageIcon("src/tree.png");
					JLabel trees = new JLabel(icon);
					trees.setBounds(12 + i * 50, 36 + j * 50, 50, 50);
					this.add(trees);
				}
			}
		}
	}

	// 添加牢房
	private void house_init() {
		Icon icon = new ImageIcon("src/house.png");
		// 1个牢房
		JLabel house1 = new JLabel(icon);
		house1.setBounds(712, 336, 50, 50);
		this.add(house1);
		tree[6][14] = 4;// 4表示牢房
		// 2个牢房
		JLabel house2 = new JLabel(icon);
		house2.setBounds(712, 386, 50, 50);
		this.add(house2);
		tree[7][14] = 4;// 4表示牢房
		// 3个牢房
		JLabel house3 = new JLabel(icon);
		house3.setBounds(712, 436, 50, 50);
		this.add(house3);
		tree[8][14] = 4;// 4表示牢房
	}

	// 添加懒羊羊
	private void sheep_init() {
		// 注意x在数组中是列，而y在数组中的是行
		Icon icon = new ImageIcon("src/sheep.png");
		// 1只羊
		JLabel sheep1 = new JLabel(icon);
		sheep1.setBounds(350 + 12, 300 + 36, 50, 50);
		this.add(sheep1);
		tree[6][7] = 2;// 2表示羊
		sheeps[6][7] = sheep1;// 将该羊的JLabel放到sheeps中
		// 2只羊
		JLabel sheep2 = new JLabel(icon);
		sheep2.setBounds(350 + 12, 400 + 36, 50, 50);
		this.add(sheep2);
		tree[8][7] = 2;// 2表示羊
		sheeps[8][7] = sheep2;
		// 3只羊
		JLabel sheep3 = new JLabel(icon);
		sheep3.setBounds(350 + 12, 450 + 36, 50, 50);
		this.add(sheep3);
		tree[9][7] = 2;// 2表示羊
		sheeps[9][7] = sheep3;
	}

	// 添加人物灰太狼
	private void wolf_init() {
		wx = 6;
		wy = 6;
		Icon icon = new ImageIcon("src/wolf_down.png");
		wolf_jb = new JLabel(icon);
		wolf_jb.setBounds(12 + wx * 50, 36 + wy * 50, 50, 50);
		this.add(wolf_jb);
	}

	// 添加背景图片
	private void backGroundInit() {
		Icon icon = new ImageIcon("src/floor.png");
		JLabel jb = new JLabel(icon);
		jb.setBounds(12, 36, 800, 600);
		this.add(jb);
	}

	public void setMainFrame() {
		this.setTitle("推箱子游戏");
		this.setSize(826, 650);
		this.setLocation(150, 30);
		this.setVisible(true);
	}

	// 判断游戏是否结束
	public void victory() {
		if (num == total) {
			JFrame jf=new JFrame();
			jf.setBounds(325, 360, 350, 200);
			Icon icon=new ImageIcon("src/victory.jpg");
			JLabel jb=new JLabel(icon);
			jb.setBounds(312, 336, 338, 146);
		    jf.add(jb);  
		 // 设置窗体大小不可改变
			jf.setResizable(false);
			// 改变窗体图标
			Toolkit kit = Toolkit.getDefaultToolkit();
			Image image = kit.createImage("src/title.gif");
			jf.setIconImage(image);
			jf.setVisible(true);
			System.out.println("胜利了");
		}
		// System.out.println(num);
	}

	// 按键类型
	@Override
	public void keyTyped(KeyEvent e) {
	}

	// 按键按下
	@Override
	public void keyPressed(KeyEvent e) {
	}

	// 按键离开
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		int x = (int) wolf_jb.getLocation().getX();
		int y = (int) wolf_jb.getLocation().getY();
		if (key == 37) {// 左边
			if (tree[wy][wx - 1] == 1) {
				return;
			}
			if (tree[wy][wx - 1] == 4) {
				num++;
				System.out.println(num);
			}
			// 羊位置的移动
			// 判断该位置是否有羊
			if (tree[wy][wx - 1] == 2) {
				if (tree[wy][wx - 2] == 1) {// 有障碍
					return;
				}
				if (tree[wy][wx - 2] == 2) {// 碰到羊
					return;
				}
				if (tree[wy][wx - 2] == 4) {// 碰到牢房
					num++;
				}
				sheeps[wy][wx - 2] = sheeps[wy][wx - 1];
				tree[wy][wx - 2] = tree[wy][wx - 1];
				tree[wy][wx - 1] = 0;
				sheeps[wy][wx - 1] = null;
				sheeps[wy][wx - 2].setLocation(x - 100, y);
			}
			wx -= 1;
			Icon icon = new ImageIcon("src/wolf_left.png");
			wolf_jb.setIcon(icon);// 对图片的更新
			wolf_jb.setLocation(x - 50, y);
		}
		if (key == 38) {// 上边
			if (tree[wy - 1][wx] == 1) {
				return;
			}
			if (tree[wy - 1][wx] == 4) {
				num++;
			}
			if (tree[wy - 1][wx] == 2) {
				if(tree[wy - 2][wx]==1){
					return;
				}
				if (tree[wy - 2][wx] == 2) {
					return;
				}
				if (tree[wy - 2][wx] == 4) {
					num++;
				}
				//羊的移动
				tree[wy - 2][wx] = tree[wy - 1][wx];
				tree[wy - 1][wx] = 0;
         		sheeps[wy - 2][wx] = sheeps[wy - 1][wx];
				sheeps[wy - 1][wx] = null;
				sheeps[wy - 2][wx].setLocation(x, y - 100);
			}
			//狼的移动
			wy -= 1;
			Icon icon = new ImageIcon("src/wolf_up.png");
			wolf_jb.setIcon(icon);
			wolf_jb.setLocation(x, y - 50);

		}
		if (key == 40) {// 下边
			if (tree[wy + 1][wx] == 1) {
				return;
			}
			if (tree[wy + 1][wx] == 4) {
				num++;
			}
			if (tree[wy + 1][wx] == 2) {
				if (tree[wy + 2][wx] == 1) {
					return;
				}
				if (tree[wy + 2][wx] == 2) {
					return;
				}
				if (tree[wy + 2][wx] == 4) {
					num++;
				}
				tree[wy + 2][wx] = tree[wy + 1][wx];
				tree[wy + 1][wx] = 0;
				sheeps[wy + 2][wx] = sheeps[wy + 1][wx];
				sheeps[wy + 1][wx] = null;
				sheeps[wy + 2][wx].setLocation(x, y + 100);
			}
			wy += 1;
			Icon icon = new ImageIcon("src/wolf_down.png");
			wolf_jb.setIcon(icon);
			wolf_jb.setLocation(x, y + 50);
		}
		if (key == 39) {// 右边
			// System.out.println(37);
			if (tree[wy][wx + 1] == 1) {
				return;
			}
			if (tree[wy][wx + 1] == 4) {
				num++;
			}
			if (tree[wy][wx + 1] == 2) {
				if (tree[wy][wx + 2] == 1) {
					return ;
				}
				if (tree[wy][wx + 2] == 2) {
					return ;
				}
				if (tree[wy][wx + 2] == 4) {
					num++;
				}
				tree[wy][wx + 2] = tree[wy][wx + 1];
				tree[wy][wx + 1] = 0;
				sheeps[wy][wx + 2] = sheeps[wy][wx + 1];
				sheeps[wy][wx + 1] = null;
				sheeps[wy][wx + 2].setLocation(x + 100, y);
			}
			//狼移动
			wx += 1;
			Icon icon = new ImageIcon("src/wolf_right.png");
			wolf_jb.setIcon(icon);
			wolf_jb.setLocation(x + 50, y);
		}

		victory();
	}
}
