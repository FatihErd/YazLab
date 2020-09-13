package yazlab2;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class main {

	static Object lock = new Object();
	static Object lock2 = new Object();
	static Server main;
	static ArrayList<Server> list;
	static JProgressBar a;
	static int sayi;
	static int sayi2;
	static int global_i = 0;
	private static JFrame frame;
	static JProgressBar progressBar;
	static JProgressBar progressBar_1;

	static class Server {
		int kapasite;
		int istek;
		String ad;

		public Server(int kapasite, int istek, String ad) {

			this.kapasite = kapasite;
			this.istek = istek;
			this.ad = ad;
		}

		public int getKapasite() {
			return kapasite;
		}

		public void setKapasite(int kapasite) {
			this.kapasite = kapasite;
		}

		public int getIstek() {
			return istek;
		}

		public void setIstek(int istek) {
			this.istek = istek;
		}

		public void stopRunning() {
			// TODO Auto-generated method stub

		}
	}

	public static int toplaMain() {

		synchronized (lock) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Random rand = new Random();
			int a = rand.nextInt(100);// gözükmesi için 200 normalde 100
			// System.out.println("main random:"+a);
			// System.out.println("ilk main thread: "+main.istek);
			main.istek += a;
			// System.out.println("main eklene :"+a);

			sayi = ((main.istek * 100) / main.kapasite);
			progressBar.setValue(sayi);
			progressBar.repaint();
			System.out.println("----------------main yüzde:" + sayi);

			int b = rand.nextInt(50);
			main.istek -= b;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println("main thread: "+main.istek);
			if ((main.istek) >= 0 && (main.istek) < main.kapasite) {
				return main.istek;
			} else if ((main.istek) < 0) {
				return 0;
			} else {
				return main.kapasite;
			}
		}
	}

	public static void Sub(Server sub) {

		synchronized (lock) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Random rand = new Random();
			int b = rand.nextInt(50);
			// System.out.println("main deðeri"+main.istek );

			if (main.istek > b) {
				main.istek = main.istek - b;
			} else {
				main.istek -= main.istek;
			}

			// System.out.println("maindeki düsüs: "+b );
			sub.istek = sub.istek + b;
			// System.out.println("suba eklenen : "+b );
			int c = rand.nextInt(50);
			if (c > sub.istek) {
				sub.istek -= sub.istek;
			} else {
				sub.istek = sub.istek - c;
			}

			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println("Adý: "+sub.ad+" Sub: "+sub.istek);
		}
	}

	public static void ST(Server ser) {

		final Server s = ser;
		Thread SubThread = new Thread(new Runnable() {
			public void run() {
				// sub.istek = Sub( sub.istek);
				Sub(s);
			}
		});
		SubThread.start();
	}

	public static void kontrol() {
		System.out.println("----Sunucu sayisi:" + (list.size() + 1));
		synchronized (lock) {
			for (int i = 0; i < list.size(); i++) {
				Server sub = list.get(i);
				// System.out.println("--"+sub.istek );
				int yuzde = ((sub.istek * 100) / sub.kapasite);
				System.out.println(i + " " + "kapasite yüzde:" + yuzde);

				progressBar_1 = new JProgressBar();
				progressBar_1.setForeground(Color.GREEN);
				progressBar_1.setFont(new Font("Arial", Font.PLAIN, 11));
				progressBar_1.setStringPainted(true);

				progressBar_1.setBounds(100, 45 + (i * 25), 146, 17);
				progressBar_1.setName(Integer.toString(i));
				progressBar_1.setValue(yuzde);
				frame.setContentPane(progressBar_1);

				if (yuzde >= 70) {
					newSub(sub);
				}
				if (yuzde == 0 && list.size() >= 3) {
					list.remove(i);
					sub.stopRunning();
					progressBar_1.setVisible(false);

				}
			}
		}
	}

	public static void newSub(Server server) {
		synchronized (lock) {
			int newserverkapasite = server.istek / 2;
			server.istek /= 2;
			System.out.println("sever kapasiyetisi yarýya düþer:" + server.istek);

			final Server newsub = new Server(250, newserverkapasite, Integer.toString(global_i++));

			Thread SubThreadolustur = new Thread(new Runnable() {
				public void run() {

					Sub(newsub);

				}
			});
			SubThreadolustur.start();
			global_i++;
			list.add(newsub);
			
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		list = new ArrayList<Server>();
		main = new Server(500, 0, "main");
		Server sub = new Server(250, 0, Integer.toString(global_i++));
		Server sub2 = new Server(250, 0, Integer.toString(global_i++));
		list.add(sub);
		list.add(sub2);

		// list.add(new Server(100,0,String.valueOf(list.size())));

		while (true) {
			Thread MainThread = new Thread(new Runnable() {

				public void run() {

					main.istek = toplaMain();
				}
			});

			for (int i = 0; i < list.size(); i++) {
				ST(list.get(i));
			}

			Thread kontrolthread = new Thread(new Runnable() {
				public void run() {
					kontrol();
				}
			});

			MainThread.start();
			kontrolthread.start();

			try {
				MainThread.join();
				kontrolthread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 279, 666);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		progressBar = new JProgressBar();
		progressBar.setBounds(100, 20, 146, 17);
		progressBar.setStringPainted(true);
		progressBar.setBackground(SystemColor.control);
		progressBar.setForeground(Color.GREEN);
		progressBar.setFont(new Font("Arial", Font.PLAIN, 11));
		frame.getContentPane().add(progressBar);

		JLabel lblNewLabel = new JLabel("MA\u0130N THREAD:");
		lblNewLabel.setBounds(10, 19, 90, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("SUB THREADS:");
		lblNewLabel_1.setBounds(10, 44, 90, 14);
		frame.getContentPane().add(lblNewLabel_1);

		// frame.getContentPane().add(progressBar_1);

	}
}