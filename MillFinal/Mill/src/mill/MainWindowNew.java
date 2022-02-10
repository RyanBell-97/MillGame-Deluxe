package mill;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;

import java.io.*;
//import java.net.*;
import java.util.Arrays;
//import java.util.StringTokenizer;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.geometry.Bounds;
//import javafx.layout.*;
//import jdk.nashorn.internal.ir.BreakNode;

public class MainWindowNew extends javax.swing.JFrame {

	
	private static javax.swing.JButton[] stoneButtonArr;
	private static javax.swing.JButton[] boardButtonArr;
	public static boolean isBlack, connected= false, stopping= false;
	private int playerID;
	public static boolean ready= false;
	public static CountDownLatch readyPlayers= new CountDownLatch(1);
	private static ClientThread ctArr[];
	public static LoginJDialog loginJDialog;
	public static MatchJDialog matchJDialog;
	private static MainWindowNew mainWindowNewArr[];
	
	public static MainWindowNew p;
	public ClientThread ct;
	
	public void connectionEstablishedGui(int playerID, boolean isBlack){
		//this.setTitle("Player #"+ playerID);
		if (isBlack) {
			this.setTitle("Player #"+ playerID+ " Color: Black");
			//jLabelTurn.setText("Black");
		} else {
			this.setTitle("Player #"+ playerID+ " Color: White");
			//jLabelTurn.setText("White");
		}
		
		if (playerID== 1) {
			System.out.println("You are player 1");
		} else {
			System.out.println("You are player 2");
		}
	}
	
	private void setUpGUI(){
		this.setTitle("Mill Game - Not connected to server");
	}
	
	public MainWindowNew() {
		this.al = new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				//jButtonStartActionPerformed(evt);
			}
		};
		initComponents();
		ct= new ClientThread();
		ctArr= new ClientThread[1];
		ctArr[0]= ct;
		mainWindowNewArr= new MainWindowNew[1];
		mainWindowNewArr[0]= this;
		loginJDialog= new LoginJDialog(p, true, ctArr);
		matchJDialog= new MatchJDialog(p, true, ctArr, mainWindowNewArr);
		
		this.getRootPane().setDefaultButton(jButtonStart);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				cleanUpAndClose();
			}
		});
	}
	
	private void cleanUpAndClose(){
		if(loginJDialog.loggedIn() && !matchJDialog.selectedOpponent){
			try {
				ct.dataOut.writeUTF("cancel");
				ct.dataOut.flush();
				ct.closeSocketAndStreams();
				System.out.println("Sent cancel");
			} catch (Exception e) {
			}
		}else if(matchJDialog.selectedOpponent){
			try {
				ct.dataOut.writeInt(100);
				ct.dataOut.flush();
				ct.closeSocketAndStreams();
			} catch (Exception e) {
			}
		}
		System.out.println("cleanUpAndClose success");
		p.dispose();
		System.exit(0);
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jButton19 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButtonS11 = new javax.swing.JButton();
        jButtonS12 = new javax.swing.JButton();
        jButtonS13 = new javax.swing.JButton();
        jButtonS21 = new javax.swing.JButton();
        jButtonS22 = new javax.swing.JButton();
        jButtonS23 = new javax.swing.JButton();
        jButtonS31 = new javax.swing.JButton();
        jButtonS32 = new javax.swing.JButton();
        jButtonS33 = new javax.swing.JButton();
        jButtonS14 = new javax.swing.JButton();
        jButtonS15 = new javax.swing.JButton();
        jButtonS16 = new javax.swing.JButton();
        jButtonS17 = new javax.swing.JButton();
        jButtonS18 = new javax.swing.JButton();
        jButtonS34 = new javax.swing.JButton();
        jButtonS25 = new javax.swing.JButton();
        jButtonS35 = new javax.swing.JButton();
        jButtonS38 = new javax.swing.JButton();
        jButtonS37 = new javax.swing.JButton();
        jButtonS28 = new javax.swing.JButton();
        jButtonS27 = new javax.swing.JButton();
        jButtonS24 = new javax.swing.JButton();
        jButtonS26 = new javax.swing.JButton();
        jButtonS36 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButtonStart = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabelTurn = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelMillCreated = new javax.swing.JLabel();

        jDialog1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog1.setAlwaysOnTop(true);
        jDialog1.setMinimumSize(new java.awt.Dimension(400, 300));
        jDialog1.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialog1.setType(java.awt.Window.Type.POPUP);
        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton19.setText("jButton19");
        jDialog1.getContentPane().add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonS11.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS11.setText("1");
        jButtonS11.setEnabled(false);
        jButtonS11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS11, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 146, 30, -1));

        jButtonS12.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS12.setText("jButton2");
        jButtonS12.setEnabled(false);
        jButtonS12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS12ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS12, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 146, 30, -1));

        jButtonS13.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS13.setText("jButton3");
        jButtonS13.setEnabled(false);
        jButtonS13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS13ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 146, 30, -1));

        jButtonS21.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS21.setText("jButton4");
        jButtonS21.setEnabled(false);
        jButtonS21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS21ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS21, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 30, -1));

        jButtonS22.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS22.setText("jButton5");
        jButtonS22.setEnabled(false);
        jButtonS22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS22ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS22, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 220, 30, -1));

        jButtonS23.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS23.setText("jButton6");
        jButtonS23.setEnabled(false);
        jButtonS23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS23ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS23, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 30, -1));

        jButtonS31.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS31.setText("jButton7");
        jButtonS31.setEnabled(false);
        jButtonS31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS31ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS31, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 292, 30, -1));

        jButtonS32.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS32.setText("jButton8");
        jButtonS32.setEnabled(false);
        jButtonS32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS32ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS32, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 292, 30, -1));

        jButtonS33.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS33.setText("jButton9");
        jButtonS33.setEnabled(false);
        jButtonS33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS33ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS33, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 292, 30, -1));

        jButtonS14.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS14.setText("jButton10");
        jButtonS14.setEnabled(false);
        jButtonS14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS14ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS14, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 367, 30, -1));

        jButtonS15.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS15.setText("jButton11");
        jButtonS15.setEnabled(false);
        jButtonS15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS15ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS15, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 588, 30, -1));

        jButtonS16.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS16.setText("jButton12");
        jButtonS16.setEnabled(false);
        jButtonS16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS16ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS16, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 590, 30, -1));

        jButtonS17.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS17.setText("jButton13");
        jButtonS17.setEnabled(false);
        jButtonS17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS17ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 30, -1));

        jButtonS18.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS18.setText("jButton14");
        jButtonS18.setEnabled(false);
        jButtonS18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS18ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 367, 30, -1));

        jButtonS34.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS34.setText("jButton15");
        jButtonS34.setEnabled(false);
        jButtonS34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS34ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS34, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 367, 30, -1));

        jButtonS25.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS25.setText("jButton16");
        jButtonS25.setEnabled(false);
        jButtonS25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS25ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS25, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 515, 30, -1));

        jButtonS35.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS35.setText("jButton17");
        jButtonS35.setEnabled(false);
        jButtonS35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS35ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS35, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, 30, -1));

        jButtonS38.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS38.setText("jButton18");
        jButtonS38.setEnabled(false);
        jButtonS38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS38ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS38, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 367, 30, -1));

        jButtonS37.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS37.setText("jButton19");
        jButtonS37.setEnabled(false);
        jButtonS37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS37ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS37, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 440, 30, -1));

        jButtonS28.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS28.setText("jButton20");
        jButtonS28.setEnabled(false);
        jButtonS28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS28ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS28, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 367, 30, -1));

        jButtonS27.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS27.setText("jButton21");
        jButtonS27.setEnabled(false);
        jButtonS27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS27ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS27, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 515, 30, -1));

        jButtonS24.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS24.setText("jButton22");
        jButtonS24.setEnabled(false);
        jButtonS24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS24ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS24, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 367, 30, -1));

        jButtonS26.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS26.setText("jButton1");
        jButtonS26.setEnabled(false);
        jButtonS26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS26ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS26, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 515, 30, -1));

        jButtonS36.setBackground(new java.awt.Color(125, 125, 125));
        jButtonS36.setText("jButton2");
        jButtonS36.setEnabled(false);
        jButtonS36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonS36ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonS36, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 440, 30, -1));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setText("jButton1");
        jButton1.setEnabled(false);
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 0, 27, -1));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setText("jButton2");
        jButton2.setEnabled(false);
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 0, 27, -1));

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setText("jButton3");
        jButton3.setEnabled(false);
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 0, 27, -1));

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setText("jButton4");
        jButton4.setEnabled(false);
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 0, 27, -1));

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setText("jButton5");
        jButton5.setEnabled(false);
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 30, 27, -1));

        jButton6.setBackground(new java.awt.Color(0, 0, 0));
        jButton6.setText("jButton6");
        jButton6.setEnabled(false);
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 30, 27, -1));

        jButton7.setBackground(new java.awt.Color(0, 0, 0));
        jButton7.setText("jButton7");
        jButton7.setEnabled(false);
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 30, 27, -1));

        jButton8.setBackground(new java.awt.Color(0, 0, 0));
        jButton8.setText("jButton8");
        jButton8.setEnabled(false);
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 30, 27, -1));

        jButton9.setBackground(new java.awt.Color(0, 0, 0));
        jButton9.setText("jButton9");
        jButton9.setEnabled(false);
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 27, -1));

        jButton10.setBackground(new java.awt.Color(220, 220, 220));
        jButton10.setText("jButton10");
        jButton10.setEnabled(false);
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 27, -1));

        jButton11.setBackground(new java.awt.Color(220, 220, 220));
        jButton11.setText("jButton11");
        jButton11.setEnabled(false);
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 27, -1));

        jButton12.setBackground(new java.awt.Color(220, 220, 220));
        jButton12.setText("jButton12");
        jButton12.setEnabled(false);
        jPanel1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 27, -1));

        jButton13.setBackground(new java.awt.Color(220, 220, 220));
        jButton13.setText("jButton13");
        jButton13.setEnabled(false);
        jPanel1.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 27, -1));

        jButton14.setBackground(new java.awt.Color(220, 220, 220));
        jButton14.setText("jButton14");
        jButton14.setEnabled(false);
        jPanel1.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 27, -1));

        jButton15.setBackground(new java.awt.Color(220, 220, 220));
        jButton15.setText("jButton15");
        jButton15.setEnabled(false);
        jPanel1.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 27, -1));

        jButton16.setBackground(new java.awt.Color(220, 220, 220));
        jButton16.setText("jButton16");
        jButton16.setEnabled(false);
        jPanel1.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 27, -1));

        jButton17.setBackground(new java.awt.Color(220, 220, 220));
        jButton17.setText("jButton17");
        jButton17.setEnabled(false);
        jPanel1.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 27, -1));

        jButton18.setBackground(new java.awt.Color(220, 220, 220));
        jButton18.setText("jButton18");
        jButton18.setEnabled(false);
        jPanel1.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 27, -1));

        jButtonStart.setText("Login");
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 170, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Turn:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 50, 20));

        jLabelTurn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jPanel1.add(jLabelTurn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 79, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/board1.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 500, 500));

        jLabelMillCreated.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelMillCreated.setForeground(new java.awt.Color(0, 0, 204));
        jLabelMillCreated.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabelMillCreated, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 500, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

	ActionListener al;
	
	private void mainLoop(int index){
		try {
			this.ct.dataOut.writeInt(index);
			this.ct.dataOut.flush();
		} catch (IOException ex) {
			System.out.println("mainLoop()");
		}
	}
	
	public void updateGUI(Board b){
		//update turn label
		if(b.turnOfBlack){
			jLabelTurn.setText("Black");
		}else{
			jLabelTurn.setText("White");
		}
		
		//update millCreated label
		if(b.millCreated()){
			if (b.turnOfBlack) {
				jLabelMillCreated.setText("Black player has created a mill!");
				jLabelMillCreated.setForeground(Color.black);
				jLabelMillCreated.setBackground(Color.gray);
			} else {
				jLabelMillCreated.setText("White player has created a mill!");
				jLabelMillCreated.setForeground(Color.white);
				jLabelMillCreated.setBackground(Color.gray);
			}
		}else{
			jLabelMillCreated.setText("");
			jLabelMillCreated.setForeground(Color.black);
			jLabelMillCreated.setBackground(null);
		}
		
		//update start stones
		int blackStartStones= b.getBlackStartStones();
		int whiteStartStones= b.getWhiteStartStones();
		for (int i = blackStartStones; i < 9; i++) {
			stoneButtonArr[i].setEnabled(false);
		}
		for (int i = whiteStartStones; i < 9; i++) {
			stoneButtonArr[i+ 9].setEnabled(false);
		}
		
		//update board buttons
		drawBoard(b.getBoardContentAsArray());
	}
	
	public void drawBoard(int boardContentArray[]){
		
		System.out.println("drawBoard: "+ Arrays.toString(boardContentArray));
		
		for (int i = 0; i < 24; i++) {
			switch (boardContentArray[i]) {
				case 0:
					boardButtonArr[i].setBackground(Color.GRAY);
					break;
				case 1:
					boardButtonArr[i].setBackground(Color.BLACK);
					break;
				case 2:
					boardButtonArr[i].setBackground(Color.WHITE);
					break;
				default:
					System.out.println("drawBoard() boardContentArray not 0 || 1 || 2");
					break;
			}
		}
		
	}
	
	public void disconnect(int gameOverOrStopped){
		if (gameOverOrStopped== 0) {
			jLabelMillCreated.setText("Game Over. You Won!");
			jLabelMillCreated.setForeground(Color.blue);
		}else if(gameOverOrStopped== 1){
			jLabelMillCreated.setText("Game Over. You Lost!");
			jLabelMillCreated.setForeground(Color.red);
		}else if(gameOverOrStopped== 2) {
			jLabelMillCreated.setText("You stopped the game.");
			jLabelMillCreated.setForeground(Color.red);
		}else if(gameOverOrStopped== 3) {
			jLabelMillCreated.setText("The other player stopped the game.");
			jLabelMillCreated.setForeground(Color.red);
		}else{
			System.out.println("disconnect(): if no match");
		}
		connected= false;
		jButtonStart.setText("Login");
		ready= false;
		readyPlayers= new CountDownLatch(1);
		loginJDialog.setLoggedIn(false);
		matchJDialog.selectedOpponent= false;
		init(false);
	}
	
	public void startButtonConnect(){
		//ct= new ClientThread();
		Thread ctThread= new Thread(ct);
		ctThread.start();
		
		//System.out.println("startButtonConnect() before while");
		//while (!connected) {
		//	System.out.println("connected? "+ connected);
		//}
		
		try {
			readyPlayers.await();
		} catch (InterruptedException ex) {
			Logger.getLogger(MainWindowNew.class.getName()).log(Level.SEVERE, null, ex);
		}
		//System.out.println("startButtonConnect() after while");
		
		jButtonStart.setText("Stop/Logout");
		if(ct.board.turnOfBlack){
			jLabelTurn.setText("Black");
		}else{
			jLabelTurn.setText("White");
		}
		jLabelMillCreated.setText("");
		init(true);
	}
	
	public void startButtonDisconnect(){
		try {
			ct.dataOut.writeInt(99);
			ct.dataOut.flush();
		} catch (IOException ex) {
			System.out.println("startButtonDisconnect()");
		}
		while (connected) {
		}
		
		try {
			readyPlayers.await();
		} catch (InterruptedException ex) {
			Logger.getLogger(MainWindowNew.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		jButtonStart.setText("Login");
		init(false);
	}
	
	public void startButtonSelectOpponent(){
		jButtonStart.setText("Select Oppnent");
	}

    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
		//LoginDialog loginDlg = new LoginDialog(p);
		//loginDlg.setVisible(true);
		if(!loginJDialog.loggedIn()) {
			//Bounds bounds;
			//bounds = this.localToScreen(this.getBoundsInLocal());
			loginJDialog.setTitle("Log in or Sign up");
			loginJDialog.setLocationRelativeTo(p);
			loginJDialog.setVisible(true);
		}else if(!matchJDialog.selectedOpponent){
			matchJDialog.setTitle("Write the name of an oppenent");
			matchJDialog.setLocationRelativeTo(p);
			matchJDialog.reset();
			matchJDialog.setVisible(true);
		}else{
			startButtonDisconnect(); //??? maybe?
		}
		if (loginJDialog.loggedIn() && !matchJDialog.selectedOpponent) {
			jButtonStart.setText("Select Opponent");
		} else if(matchJDialog.selectedOpponent) {
			jButtonStart.setText("Stop/Logout");
		}
		
		//jDialog1.setVisible(true);
		
//		if (!connected) {
//			startButtonConnect();
//		}else{
//			startButtonDisconnect();
//		}
    }//GEN-LAST:event_jButtonStartActionPerformed

	// <editor-fold defaultstate="collapsed" desc="Buttons">
    private void jButtonS11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS11ActionPerformed
        mainLoop(0);
    }//GEN-LAST:event_jButtonS11ActionPerformed

    private void jButtonS12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS12ActionPerformed
        mainLoop(1);
    }//GEN-LAST:event_jButtonS12ActionPerformed

    private void jButtonS13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS13ActionPerformed
        mainLoop(2);
    }//GEN-LAST:event_jButtonS13ActionPerformed

    private void jButtonS21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS21ActionPerformed
        mainLoop(8);
    }//GEN-LAST:event_jButtonS21ActionPerformed

    private void jButtonS22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS22ActionPerformed
        mainLoop(9);
    }//GEN-LAST:event_jButtonS22ActionPerformed

    private void jButtonS23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS23ActionPerformed
        mainLoop(10);
    }//GEN-LAST:event_jButtonS23ActionPerformed

    private void jButtonS31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS31ActionPerformed
        mainLoop(16);
    }//GEN-LAST:event_jButtonS31ActionPerformed

    private void jButtonS32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS32ActionPerformed
        mainLoop(17);
    }//GEN-LAST:event_jButtonS32ActionPerformed

    private void jButtonS33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS33ActionPerformed
        mainLoop(18);
    }//GEN-LAST:event_jButtonS33ActionPerformed

    private void jButtonS18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS18ActionPerformed
        mainLoop(7);
    }//GEN-LAST:event_jButtonS18ActionPerformed

    private void jButtonS28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS28ActionPerformed
        mainLoop(15);
    }//GEN-LAST:event_jButtonS28ActionPerformed

    private void jButtonS38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS38ActionPerformed
        mainLoop(23);
    }//GEN-LAST:event_jButtonS38ActionPerformed

    private void jButtonS34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS34ActionPerformed
        mainLoop(19);
    }//GEN-LAST:event_jButtonS34ActionPerformed

    private void jButtonS24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS24ActionPerformed
        mainLoop(11);
    }//GEN-LAST:event_jButtonS24ActionPerformed

    private void jButtonS14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS14ActionPerformed
        mainLoop(3);
    }//GEN-LAST:event_jButtonS14ActionPerformed

    private void jButtonS37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS37ActionPerformed
        mainLoop(22);
    }//GEN-LAST:event_jButtonS37ActionPerformed

    private void jButtonS36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS36ActionPerformed
        mainLoop(21);
    }//GEN-LAST:event_jButtonS36ActionPerformed

    private void jButtonS35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS35ActionPerformed
        mainLoop(20);
    }//GEN-LAST:event_jButtonS35ActionPerformed

    private void jButtonS27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS27ActionPerformed
        mainLoop(14);
    }//GEN-LAST:event_jButtonS27ActionPerformed

    private void jButtonS26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS26ActionPerformed
        mainLoop(13);
    }//GEN-LAST:event_jButtonS26ActionPerformed

    private void jButtonS25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS25ActionPerformed
        mainLoop(12);
    }//GEN-LAST:event_jButtonS25ActionPerformed

    private void jButtonS17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS17ActionPerformed
        mainLoop(6);
    }//GEN-LAST:event_jButtonS17ActionPerformed

    private void jButtonS16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS16ActionPerformed
        mainLoop(5);
    }//GEN-LAST:event_jButtonS16ActionPerformed

    private void jButtonS15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonS15ActionPerformed
        mainLoop(4);
    }//GEN-LAST:event_jButtonS15ActionPerformed
	// </editor-fold>

	
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainWindowNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainWindowNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainWindowNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainWindowNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				p= new MainWindowNew();
				p.setUpGUI();
				p.setVisible(true);
			}
		});
	}
	
	public static void init(boolean init){
		stoneButtonArr= new javax.swing.JButton[18];
		javax.swing.JButton temp[]= {jButton1, jButton2, jButton3, jButton4, jButton5, jButton6
		, jButton7, jButton8, jButton9, jButton10, jButton11, jButton12
		, jButton13, jButton14, jButton15, jButton16, jButton17, jButton18};
		stoneButtonArr= temp;
		temp= null;
		
		boardButtonArr= new javax.swing.JButton[24];
		javax.swing.JButton temp2[]= {jButtonS11, jButtonS12, jButtonS13, jButtonS14, jButtonS15, 
		jButtonS16, jButtonS17, jButtonS18, jButtonS21, jButtonS22, jButtonS23, jButtonS24, 
		jButtonS25, jButtonS26, jButtonS27, jButtonS28, jButtonS31, jButtonS32, jButtonS33, 
		jButtonS34, jButtonS35, jButtonS36, jButtonS37, jButtonS38};
		boardButtonArr= temp2;
		temp2= null;
		if (init) {
			enableButtons();
		} else {
			disableButtons();
			p.setTitle("Mill Game - Not connected to server");
		}
	}
	
	public static void enableButtons(){
		for (JButton jButton : stoneButtonArr) {
			jButton.setEnabled(true);
		}
		
		for (JButton jButton : boardButtonArr) {
			jButton.setEnabled(true);
			jButton.setBackground(Color.GRAY);
		}
	}
	
	public static void disableButtons(){
		for (JButton jButton : stoneButtonArr) {
			jButton.setEnabled(false);
		}
		
		for (JButton jButton : boardButtonArr) {
			jButton.setEnabled(false);
			jButton.setBackground(Color.GRAY);
		}
	}
	
	//<editor-fold defaultstate="collapsed" desc="buttons declarations">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton jButton1;
    private static javax.swing.JButton jButton10;
    private static javax.swing.JButton jButton11;
    private static javax.swing.JButton jButton12;
    private static javax.swing.JButton jButton13;
    private static javax.swing.JButton jButton14;
    private static javax.swing.JButton jButton15;
    private static javax.swing.JButton jButton16;
    private static javax.swing.JButton jButton17;
    private static javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private static javax.swing.JButton jButton2;
    private static javax.swing.JButton jButton3;
    private static javax.swing.JButton jButton4;
    private static javax.swing.JButton jButton5;
    private static javax.swing.JButton jButton6;
    private static javax.swing.JButton jButton7;
    private static javax.swing.JButton jButton8;
    private static javax.swing.JButton jButton9;
    private static javax.swing.JButton jButtonS11;
    private static javax.swing.JButton jButtonS12;
    private static javax.swing.JButton jButtonS13;
    private static javax.swing.JButton jButtonS14;
    private static javax.swing.JButton jButtonS15;
    private static javax.swing.JButton jButtonS16;
    private static javax.swing.JButton jButtonS17;
    private static javax.swing.JButton jButtonS18;
    private static javax.swing.JButton jButtonS21;
    private static javax.swing.JButton jButtonS22;
    private static javax.swing.JButton jButtonS23;
    private static javax.swing.JButton jButtonS24;
    private static javax.swing.JButton jButtonS25;
    private static javax.swing.JButton jButtonS26;
    private static javax.swing.JButton jButtonS27;
    private static javax.swing.JButton jButtonS28;
    private static javax.swing.JButton jButtonS31;
    private static javax.swing.JButton jButtonS32;
    private static javax.swing.JButton jButtonS33;
    private static javax.swing.JButton jButtonS34;
    private static javax.swing.JButton jButtonS35;
    private static javax.swing.JButton jButtonS36;
    private static javax.swing.JButton jButtonS37;
    private static javax.swing.JButton jButtonS38;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private static javax.swing.JLabel jLabelMillCreated;
    private static javax.swing.JLabel jLabelTurn;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
	//</editor-fold>
}
