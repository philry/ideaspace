package com.itany.netClass.util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class MineSweepingGame {

	JFrame jf;//界面
	
	JPanel body;//主体
	JLabel timeLabel;//计时标签
	JLabel thunderLabel;//地雷提示
	
	MyPanel[][] tds;//格子
	int lv = 0;//等级,0,1,2
	final String[] gameLvs = { "1", "2", "3" };
	final int[] sizes = { 9, 16, 19 };//不同等级的桌面大小
	final int[] thunders = { 10, 40, 99 };//不同等级的雷的数量
	
	//重新开始时,需要重置这些值-begin
	int mark = 0;//被标记数
	int trueMarkThunder = 0;//被标记的真正的雷的数量
	int open = 0;//被点开的
	long time = 0;//计时
	boolean exit = false;//用于结束线程
	//重新开始时,需要重置这些值-end
	
	boolean[][] thundersArr;//记录雷的位置
	
	Random r = new Random();//随机
	
	static Image thunderImg;//地雷图片
	static Image markImg;//标记图片
	
	int width = 30;//基础宽度
	int height = 40;//基础高度
	
	Font f = new Font("楷书", Font.BOLD, 16);//显示的字体
	Border borderClose;//统一点开边框
	Border borderOpen;//统一已点开边框
	
	private void init(){
		thunderImg = new ImageIcon(MyPanel.class.getClassLoader().getResource("thunder.jpg")).getImage();
		markImg = new ImageIcon(MyPanel.class.getClassLoader().getResource("mark.jpg")).getImage();
		jf = new JFrame("扫雷");
		
		//等级选择
		String dialog = "0";
		while(true){
			dialog = JOptionPane.showInputDialog(jf, "请输入游戏等级(1,2,3)");
			if(null == dialog){//取消,关闭时为null
				System.exit(0);//退出
			}
			boolean flag = true;//是否正确选择了游戏等级,false有,true没有
			for (int i = 0; i < gameLvs.length; i++) {
				if(gameLvs[i].equals(dialog)){
					flag = false;
				}
			}
			if(flag){
				JOptionPane.showMessageDialog(jf, "游戏等级共3级:1,2,3", "提示", JOptionPane.INFORMATION_MESSAGE);
			}else{
				break;
			}
		}
		lv = Integer.parseInt(dialog) - 1;
		//初始JFrame等组件
		jf.setSize(width * sizes[lv] + 8, height * sizes[lv]);
		jf.setLayout(null);
		jf.setResizable(false);//不可缩放
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭时退出程序
		
		timeLabel = new JLabel();
		timeLabel.setFont(f);
		timeLabel.setBounds(width, 0, 150, 50);
		thunderLabel = new JLabel();
		thunderLabel.setFont(f);
		thunderLabel.setBounds(width * sizes[lv] - width - 100, 0, 100, 50);
		
		jf.add(timeLabel);
		jf.add(thunderLabel);
		//设置布局管理器----网格矩阵形式的布局
		GridLayout layout = new GridLayout(sizes[lv], sizes[lv]);
		body = new JPanel();
		body.setLayout(layout);
		body.setBounds(0, 50, width * sizes[lv], width * sizes[lv]);
		jf.add(body);
		
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	
	//初始化游戏桌面
	private void initTable(){
		tds = new MyPanel[sizes[lv]][sizes[lv]];
		thundersArr = new boolean[sizes[lv]][sizes[lv]];
		int surplus = thunders[lv];//为初始化的雷
		for (int i = 0; i < surplus; i++) {
			//生成雷的位置到thundersArr数组中
			setThunder();
		}
		//初始化每个小单元格
		for (int i = 0; i < tds.length; i++) {
			for (int j = 0; j < tds[i].length; j++) {
				boolean has = thundersArr[i][j];
				//创建单元格,设置属性,是否是雷
				MyPanel panel = new MyPanel(has, i, j);
				//TODO: 生成边框
				// 斜面边框,有立体感.
				//type: BevelBorder.LOWERED边框凸出内容凹陷, BevelBorder.RAISED边框凹陷内容凸出
				borderClose = BorderFactory.createBevelBorder(BevelBorder.RAISED);
				borderOpen = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
				
				// 线条边框--------不好看
				// color: 线条颜色,thickness: 线宽(默认为1px),rounded: 是否有圆角
//				borderClose = BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true);
//				borderOpen = BorderFactory.createLineBorder(Color.DARK_GRAY, 2, false);
				
				panel.setBorder(borderClose);
				panel.setBackground(Color.LIGHT_GRAY);
				//创建自定义的MouseListener
				MyMouseAdapter mouseAdapter = new MyMouseAdapter(panel, this);
				//设置点击事件
				panel.addMouseListener(mouseAdapter);
				panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				tds[i][j] = panel;
				body.add(panel);
			}
		}
		
		for (int i = 0; i < tds.length; i++) {
			for (int j = 0; j < tds[i].length; j++) {
				boolean isThunder = thundersArr[i][j];
				//不是雷则,计算四周的地雷数量
				if(!isThunder){
					int countAroundThunders = countAroundThunders(i, j);
					tds[i][j].count = countAroundThunders;
				}
			}
		}
		
		
	}
	
	private int countAroundThunders(int x, int y){
		//边界
		int begin = 0;
		int end = sizes[lv];
		int count = 0;
		//xy
		//00  01  02
		//10 (11) 12
		//20  21  22
		//八个位置
		//上
		if((x - 1) >= begin){
			boolean isThunder = thundersArr[x - 1][y];
			if(isThunder){
				count++;
			}
		}
		//右上
		if((x - 1) >= begin && (y + 1) < end){
			boolean isThunder = thundersArr[x - 1][y + 1];
			if(isThunder){
				count++;
			}
		}
		//右
		if((y + 1) < end){
			boolean isThunder = thundersArr[x][y + 1];
			if(isThunder){
				count++;
			}
		}
		//右下
		if((x + 1) < end && (y + 1) < end){
			boolean isThunder = thundersArr[x + 1][y + 1];
			if(isThunder){
				count++;
			}
		}
		//下
		if((x + 1) < end){
			boolean isThunder = thundersArr[x + 1][y];
			if(isThunder){
				count++;
			}
		}
		//左下
		if((x + 1) < end && (y - 1) >= begin){
			boolean isThunder = thundersArr[x + 1][y - 1];
			if(isThunder){
				count++;
			}
		}
		//左
		if((y - 1) >= begin){
			boolean isThunder = thundersArr[x][y - 1];
			if(isThunder){
				count++;
			}
		}
		//左上
		if((x - 1) >= begin && (y - 1) >= begin){
			boolean isThunder = thundersArr[x - 1][y - 1];
			if(isThunder){
				count++;
			}
		}
		return count;
	}
	
	//生成地雷的位置,不可重复放置
	private void setThunder(){
		int size = sizes[lv];//桌面尺寸
		boolean has = true;//当前位置有地雷
		while(has){//有,生成新的位置坐标
			int x = r.nextInt(size);
			int y = r.nextInt(size);
			has = thundersArr[x][y];
			if(!has){//没有,放到当前位置坐标
				thundersArr[x][y] = true;
				//System.out.println(x + "," + y);
			}
		}
	}
	
	//显示"0"方块的周围连续的方块(未打开的,不是雷的方块)
	//type 执行哪种操作 null(非提示相关操作) 1滚轮按压(给提示)2滚轮按压释放(取消提示)
	private int autoClearZero(int x, int y, Integer type){
		//边界
		int begin = 0;
		int end = sizes[lv];
		int count = 0;
		//xy
		//00  01  02
		//10 (11) 12
		//20  21  22
		//八个位置
		//上
		if((x - 1) >= begin){
			MyPanel p = tds[x - 1][y];
//			System.out.println(p.siteX + "=" + p.siteY + "==" + p.count);
			count+=doAutoOpen(p, type);
		}
		//右上
		if((x - 1) >= begin && (y + 1) < end){
			MyPanel p = tds[x - 1][y + 1];
//			System.out.println(p.siteX + "=" + p.siteY + "==" + p.count);
			count+=doAutoOpen(p, type);
		}
		//右
		if((y + 1) < end){
			MyPanel p = tds[x][y + 1];
//			System.out.println(p.siteX + "=" + p.siteY + "==" + p.count);
			count+=doAutoOpen(p, type);
		}
		//右下
		if((x + 1) < end && (y + 1) < end){
			MyPanel p = tds[x + 1][y + 1];
//			System.out.println(p.siteX + "=" + p.siteY + "==" + p.count);
			count+=doAutoOpen(p, type);
		}
		//下
		if((x + 1) < end){
			MyPanel p = tds[x + 1][y];
//			System.out.println(p.siteX + "=" + p.siteY + "==" + p.count);
			count+=doAutoOpen(p, type);
		}
		//左下
		if((x + 1) < end && (y - 1) >= begin){
			MyPanel p = tds[x + 1][y - 1];
//			System.out.println(p.siteX + "=" + p.siteY + "==" + p.count);
			count+=doAutoOpen(p, type);
		}
		//左
		if((y - 1) >= begin){
			MyPanel p = tds[x][y - 1];
//			System.out.println(p.siteX + "=" + p.siteY + "==" + p.count);
			count+=doAutoOpen(p, type);
		}
		//左上
		if((x - 1) >= begin && (y - 1) >= begin){
			MyPanel p = tds[x - 1][y - 1];
//			System.out.println(p.siteX + "=" + p.siteY + "==" + p.count);
			count+=doAutoOpen(p, type);
		}
		return count;
	}
	//type 执行哪种操作:
	//  null 非提示相关操作
	//  1 滚轮按压(给提示)
	//  2 滚轮按压释放(取消提示)
	//  3 当标记的数量大于等于方格中的数字时,自动打开其他方格
	private int doAutoOpen(MyPanel p, Integer type){
		if(null == type){
			if(!p.click && !p.thunder){
				p.click = true;
				open++;
				p.updateUI();
				//是"0"方块,继续点开周围
				if(0 == p.count){
					autoClearZero(p.siteX, p.siteY, type);
				}
			}
		}else if(!p.click && !p.mark){//未点开且未被标记的才需要提示
			//1滚轮按压(给提示)2滚轮按压释放(取消提示)
			switch (type) {
			case 1:
				p.setBackground(Color.CYAN);
				break;
			case 2:
				p.setBackground(Color.LIGHT_GRAY);
				break;
			case 3:
				// 如果是雷,over
				if(p.thunder){
					gameClear("你输了");
					break;
				}
				p.click = true;
				open++;
				p.updateUI();
				//是"0"方块,继续点开周围
				if(0 == p.count){
					autoClearZero(p.siteX, p.siteY, type);
				}
				break;
			default:
				break;
			}
		}
		//被标记 返回1
		if(p.mark){
			return 1;
		}
		return 0;
	}
	
	private void gameClear(String msg){
		exit = true;//用于结束线程
		mark = 0;//被标记数
		trueMarkThunder = 0;//被标记的真正的雷的数量
		open = 0;//被点开的
		time = 0;//计时
		
		JOptionPane.showMessageDialog(jf, msg, "信息", JOptionPane.INFORMATION_MESSAGE);
		int confirmDialog = JOptionPane.showConfirmDialog(jf, "是否重新开始游戏", "信息", JOptionPane.YES_NO_OPTION);
		jf.dispose();
		if(JOptionPane.OK_OPTION == confirmDialog){//选择重新开始
			exit = false;
			start();
		}
	}
	
	public void start(){
		init();
		
		initTable();
//		body.revalidate();//更新组件
//		body.repaint();//更新组件
		body.updateUI();//更新组件
		
		Thread t = new Thread(){
			@Override
			public void run() {
				while(!exit){
					try {
						timeLabel.setText("用时: " + time + " 秒");
						thunderLabel.setText("剩余 " + (thunders[lv] - mark) + " 个雷");
//						System.out.println("mark=" + mark);
//						System.out.println("open=" + open);
//						System.out.println("雷total=" + thunders[lv]);
//						System.out.println("total=" + (sizes[lv] * sizes[lv]));
						//雷标记全对,未点中雷-全点开
						if(thunders[lv] == trueMarkThunder || open == (sizes[lv] * sizes[lv] - thunders[lv])){
							gameClear("你赢了");
						}
						time++;
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
	}
	
	class MyPanel extends JPanel {
		private static final long serialVersionUID = -3868521901675925695L;
		boolean thunder;
		int count;
		boolean mark;
		boolean click = false;//被点开
		int siteX;
		int siteY;
		
		public MyPanel(boolean thunder, int siteX, int siteY) {
			super();
			this.thunder = thunder;
			this.siteX = siteX;
			this.siteY = siteY;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Color c = g.getColor();
			g.drawRect(0, 0, this.getWidth(), this.getHeight());
			if(click){
				if(thunder){
					//下面这行是为了背景图片可以跟随窗口自行调整大小,可以自己设置成固定大小  
					g.drawImage(thunderImg, 0, 0, this.getWidth(), this.getHeight(), this);
				}else{
					this.setBorder(borderOpen);
					if(0 == count){
						g.setColor(Color.PINK);
						g.fillRect(0, 0, this.getWidth(), this.getHeight());
						g.setColor(c);
					}else{
						g.setColor(new Color(0x7C, 0xA3, 0xD5));
						g.fillRect(0, 0, this.getWidth(), this.getHeight());
						g.setColor(Color.BLACK);
						g.setFont(f);
						g.drawString(count+"", this.getWidth()/2 - 4, this.getHeight()/2 + 4);
						g.setColor(c);
					}
				}
			}
			if(mark){
				g.drawImage(markImg, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		}
	}
	
	//鼠标事件适配器
	class MyMouseAdapter extends MouseAdapter {
		MyPanel panel;//单元格
		MineSweepingGame t;//游戏主体对象
		public MyMouseAdapter(MyPanel panel, MineSweepingGame t) {
			super();
			this.panel = panel;
			this.t = t;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(!panel.click){//未打开的不能有提示
				return;
			}
			int button = e.getButton();
			switch (button) {
			case 2://滚轮按住,给 周围的可左击的方格 添加标记
				int x = panel.siteX;
				int y = panel.siteY;
				autoClearZero(x, y, 1);//被标记的数量
				break;
			default:
				break;
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if(!panel.click){//未打开的不能有提示
				return;
			}
			int button = e.getButton();
			switch (button) {
				case 2://滚轮松开,去除 周围的可左击的方格 的标记
					int x = panel.siteX;
					int y = panel.siteY;
					int count = autoClearZero(x, y, 2);//被标记的数量
					//当标记的数量大于等于方格中的数字时,自动打开其他方格
					if(count >= panel.count){
						autoClearZero(x, y, 3);//强制打开玩家认为不是雷的方格
					}
					break;
				default:
					break;
			}
		}
		//鼠标按键在组件上单击（按下并释放）时调用
		@Override
		public void mouseClicked(MouseEvent e) {
			//1左击
			//3右击
			int button = e.getButton();//获取鼠标的按键
//			System.out.println("Button=" + button);
			switch (button) {
			case 1://左击
				//点开
				//如果已标记,则不可点开
				if(panel.mark){
					break;
				}
				//是雷
				if(panel.thunder){
					t.gameClear("你输了");
					break;
				}
				if(!panel.click){//如果未被点过,计数
					t.open++;//点击计数
					panel.click = true;//设置为已点开
					if(0 == panel.count){//是"0"方块,自动打开周围可显示的
						t.autoClearZero(panel.siteX, panel.siteY, null);
					}
					panel.updateUI();//更新组件,重绘图片
					break;
				}
			case 3://右击
				if(panel.click){//已被点开不能标记
					break;
				}
				//未标记该为已标记,已标记该为未标记
				boolean mark = panel.mark;
				if(mark){//去除标记
					t.mark--;//标记计数
					if(panel.thunder){
						trueMarkThunder--;//正确标记雷的计数
					}
				}else{//做标记
					t.mark++;//标记计数
					if(panel.thunder){
						trueMarkThunder++;//正确标记雷的计数
					}
				}
				panel.mark = !mark;//改变标记
				panel.updateUI();//更新组件,重绘图片
				break;
			default:
				break;
			}
			
		}

	}
	
	public static void main(String[] args) {
//		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//		int run = compiler.run(null, null, null, "E:/eclipse/pet/src/main/java/com/itany/util/Test2.java");
//		System.out.println(run);
//		
//		try {
//			Class<?> c = Class.forName("com.itany.util.Test2");
//			Field[] fields = c.getDeclaredFields();
//			System.out.println(Arrays.toString(fields));
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}

		MineSweepingGame t = new MineSweepingGame();
		t.start();
		
		
//		t.test();
	}
	
	public void test(){
		JFrame jf = new JFrame("tt");
		//初始JFrame等组件
		jf.setSize(width * sizes[lv] + 8, height * sizes[lv]);
		//设置布局管理器----网格矩阵形式的布局
		GridLayout layout = new GridLayout(sizes[lv], sizes[lv]);
		jf.setLayout(layout);
//		jf.setResizable(false);//不可缩放
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭时退出程序
		int size = 81;
		for (int i = 0; i < size; i++) {
			JPanel p = new JPanel();
//			Border border = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
			Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
			p.setBackground(Color.PINK);
			p.setBorder(border);
			jf.add(p);
		}
		
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	
}
