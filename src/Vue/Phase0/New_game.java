/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change window license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit window template
 */
package Vue.Phase0;
import Controleur.*;
import Modeles.InitPartie;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class New_game {
	
	public JFrame window;
	public InitPartie partie;
	Dimension tailleEcran;
	public int screenHeight, screenWidth, frameHeight, frameWidth;

    public New_game(JFrame window, InitPartie partie) {
    	this.partie=partie;
    	this.window=window;
    	
        initComponents();
        enable_bp();
        this.tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        this.frameWidth=this.window.getWidth();
        this.frameHeight=this.window.getHeight();
        this.screenWidth=tailleEcran.width;
        this.screenHeight=tailleEcran.height;
        this.window.setLocation(screenWidth/2-frameWidth/2, screenHeight/2-frameHeight/2);
        this.window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.window.setTitle("Nouvelle Partie");
        this.window.setMinimumSize(new java.awt.Dimension(805, 406));
        this.window.setResizable(true);
        this.window.setVisible(true);
    }

    private void initComponents() {
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        bp_GAMEMODE_IA_IA = new javax.swing.JRadioButton();
        bp_GAMEMODE_IA_PLAYER = new javax.swing.JRadioButton();
        bp_GAMEMODE_PLAYER_PLAYER = new javax.swing.JRadioButton();
        bp_NAMEPLAYER_J3 = new javax.swing.JTextField();
        bp_NAMEPLAYER_J1 = new javax.swing.JTextField();
        bp_IA3_Level_3 = new javax.swing.JRadioButton();
        bp_NAMEPLAYER_J2 = new javax.swing.JTextField();
        bp_START = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bp_IA1_Level_1 = new javax.swing.JRadioButton();
        bp_IA1_Level_2 = new javax.swing.JRadioButton();
        bp_IA1_Level_3 = new javax.swing.JRadioButton();
        bp_IA2_Level_1 = new javax.swing.JRadioButton();
        bp_IA2_Level_2 = new javax.swing.JRadioButton();
        bp_IA2_Level_3 = new javax.swing.JRadioButton();
        bp_IA3_Level_1 = new javax.swing.JRadioButton();
        bp_IA3_Level_2 = new javax.swing.JRadioButton();
        
        jPanel2.setFocusCycleRoot(true);
        jPanel2.setFocusTraversalPolicyProvider(true);

        jPanel1.setBackground(new java.awt.Color(175, 200, 211));
        jPanel1.setFocusable(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(779, 600));

        jLabel5.setBackground(new java.awt.Color(176, 200, 211));
        jLabel5.setFont(new java.awt.Font("Source Sans Pro Black", 3, 24)); // NOI18N
        jLabel5.setText("     Nouvelle Partie");
        jLabel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 51, 255), 3, true));
        jLabel5.setDoubleBuffered(true);

        jLabel6.setBackground(new java.awt.Color(176, 200, 211));
        jLabel6.setFont(new java.awt.Font("Source Code Pro Semibold", 3, 12)); // NOI18N
        jLabel6.setText("Difficulté");
        jLabel6.setOpaque(true);

        jLabel7.setBackground(new java.awt.Color(176, 200, 211));
        jLabel7.setFont(new java.awt.Font("Source Code Pro Semibold", 3, 12)); // NOI18N
        jLabel7.setText("Nom Joueur");
        jLabel7.setOpaque(true);

        jLabel8.setBackground(new java.awt.Color(176, 200, 211));
        jLabel8.setFont(new java.awt.Font("Source Code Pro Semibold", 3, 12)); // NOI18N
        jLabel8.setText("Difficulté");
        jLabel8.setOpaque(true);

        jLabel9.setBackground(new java.awt.Color(176, 200, 211));
        jLabel9.setFont(new java.awt.Font("Source Code Pro Semibold", 3, 12)); // NOI18N
        jLabel9.setText("Nom Joueur 2");

        jLabel10.setBackground(new java.awt.Color(176, 200, 211));
        jLabel10.setFont(new java.awt.Font("Source Code Pro Semibold", 3, 12)); // NOI18N
        jLabel10.setText("Nom Joueur 1");

        jSeparator2.setBackground(new java.awt.Color(0, 51, 51));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        bp_GAMEMODE_IA_IA.setBackground(new java.awt.Color(176, 200, 211));
        bp_GAMEMODE_IA_IA.setFont(new java.awt.Font("Source Code Pro Semibold", 0, 14)); // NOI18N
        bp_GAMEMODE_IA_IA.setText("IA VS IA");
        bp_GAMEMODE_IA_IA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bp_GAMEMODE_IA_IA.setDoubleBuffered(true);
        bp_GAMEMODE_IA_IA.setFocusCycleRoot(true);
        bp_GAMEMODE_IA_IA.setFocusTraversalPolicyProvider(true);
        bp_GAMEMODE_IA_IA.setOpaque(true);
        bp_GAMEMODE_IA_IA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bp_GAMEMODE_IA_IAActionPerformed(evt);
            }
        });

        bp_GAMEMODE_IA_PLAYER.setBackground(new java.awt.Color(176, 200, 211));
        bp_GAMEMODE_IA_PLAYER.setFont(new java.awt.Font("Source Code Pro Semibold", 0, 14)); // NOI18N
        bp_GAMEMODE_IA_PLAYER.setText("Joueur VS IA");
        bp_GAMEMODE_IA_PLAYER.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bp_GAMEMODE_IA_PLAYER.setDoubleBuffered(true);
        bp_GAMEMODE_IA_PLAYER.setFocusCycleRoot(true);
        bp_GAMEMODE_IA_PLAYER.setFocusTraversalPolicyProvider(true);
        bp_GAMEMODE_IA_PLAYER.setOpaque(true);
        bp_GAMEMODE_IA_PLAYER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bp_GAMEMODE_IA_PLAYERActionPerformed(evt);
            }
        });

        bp_GAMEMODE_PLAYER_PLAYER.setBackground(new java.awt.Color(176, 200, 211));
        bp_GAMEMODE_PLAYER_PLAYER.setFont(new java.awt.Font("Source Code Pro Semibold", 0, 14)); // NOI18N
        bp_GAMEMODE_PLAYER_PLAYER.setText("Joueur1 VS Joueur2");
        bp_GAMEMODE_PLAYER_PLAYER.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bp_GAMEMODE_PLAYER_PLAYER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bp_GAMEMODE_PLAYER_PLAYERActionPerformed(evt);
            }
        });

        bp_NAMEPLAYER_J3.setBackground(new java.awt.Color(176, 200, 211));
        bp_NAMEPLAYER_J3.setForeground(new java.awt.Color(51, 51, 51));
        bp_NAMEPLAYER_J3.setText("NomJoueur");
        bp_NAMEPLAYER_J3.setDisabledTextColor(new java.awt.Color(45, 70, 81));
        bp_NAMEPLAYER_J3.setEnabled(false);
        bp_NAMEPLAYER_J3.setOpaque(true);
        bp_NAMEPLAYER_J3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bp_NAMEPLAYER_J3MouseClicked(evt);
            }
        });
        bp_NAMEPLAYER_J3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bp_NAMEPLAYER_J3ActionPerformed(evt);
            }
        });

        bp_NAMEPLAYER_J1.setBackground(new java.awt.Color(176, 200, 211));
        bp_NAMEPLAYER_J1.setForeground(new java.awt.Color(51, 51, 51));
        bp_NAMEPLAYER_J1.setText("NomJoueur1");
        bp_NAMEPLAYER_J1.setDisabledTextColor(new java.awt.Color(45, 70, 81));
        bp_NAMEPLAYER_J1.setDoubleBuffered(true);
        bp_NAMEPLAYER_J1.setEnabled(false);
        bp_NAMEPLAYER_J1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bp_NAMEPLAYER_J1MouseClicked(evt);
            }
        });
        bp_NAMEPLAYER_J1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bp_NAMEPLAYER_J1ActionPerformed(evt);
            }
        });

        bp_IA3_Level_3.setText("Difficile");

        bp_NAMEPLAYER_J2.setBackground(new java.awt.Color(176, 200, 211));
        bp_NAMEPLAYER_J2.setForeground(new java.awt.Color(51, 51, 51));
        bp_NAMEPLAYER_J2.setText("NomJoueur2");
        bp_NAMEPLAYER_J2.setDisabledTextColor(new java.awt.Color(45, 70, 81));
        bp_NAMEPLAYER_J2.setEnabled(false);
        bp_NAMEPLAYER_J2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bp_NAMEPLAYER_J2MouseClicked(evt);
            }
        });
        bp_NAMEPLAYER_J2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bp_NAMEPLAYER_J2ActionPerformed(evt);
            }
        });

        bp_START.setBackground(new java.awt.Color(176, 200, 211));
        bp_START.setFont(new java.awt.Font("Source Code Pro Semibold", 1, 14)); // NOI18N
        bp_START.setText("Lancer ->");
        bp_START.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51), 3));
        bp_START.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bp_START.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bp_STARTActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(45, 70, 81));
        jLabel1.setFont(new java.awt.Font("Source Code Pro Semibold", 3, 12)); // NOI18N
        jLabel1.setText("IA 1");

        jLabel2.setBackground(new java.awt.Color(45, 70, 81));
        jLabel2.setFont(new java.awt.Font("Source Code Pro Semibold", 3, 12)); // NOI18N
        jLabel2.setText("IA 2");

        bp_IA1_Level_1.setText("Facile");
        bp_IA1_Level_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bp_IA1_Level_1ActionPerformed(evt);
            }
        });

        bp_IA1_Level_2.setText("Moyen");
        bp_IA1_Level_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bp_IA1_Level_2ActionPerformed(evt);
            }
        });

        bp_IA1_Level_3.setText("Difficile");
        bp_IA1_Level_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bp_IA1_Level_3ActionPerformed(evt);
            }
        });

        bp_IA2_Level_1.setText("Facile");
        bp_IA2_Level_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bp_IA2_Level_1ActionPerformed(evt);
            }
        });

        bp_IA2_Level_2.setText("Moyen");
        bp_IA2_Level_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bp_IA2_Level_2ActionPerformed(evt);
            }
        });

        bp_IA2_Level_3.setText("Difficile");

        bp_IA3_Level_1.setText("Facile");
        bp_IA3_Level_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bp_IA3_Level_1ActionPerformed(evt);
            }
        });

        bp_IA3_Level_2.setText("Moyen");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(97, 97, 97))
                            .addComponent(bp_GAMEMODE_IA_IA, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(bp_IA1_Level_1))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bp_IA2_Level_1)
                                            .addComponent(bp_IA1_Level_2)
                                            .addComponent(bp_IA2_Level_2)
                                            .addComponent(bp_IA1_Level_3)
                                            .addComponent(bp_IA2_Level_3))))
                                .addGap(22, 22, 22)))
                        .addGap(51, 51, 51)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(bp_GAMEMODE_IA_PLAYER, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(bp_START, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(bp_IA3_Level_2)
                                                    .addComponent(bp_IA3_Level_1)
                                                    .addComponent(bp_IA3_Level_3)))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(bp_NAMEPLAYER_J3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(22, 22, 22)))
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bp_NAMEPLAYER_J2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(33, 33, 33)
                            .addComponent(bp_NAMEPLAYER_J1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(bp_GAMEMODE_PLAYER_PLAYER, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bp_GAMEMODE_IA_IA)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(bp_IA1_Level_1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bp_IA1_Level_2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bp_GAMEMODE_PLAYER_PLAYER)
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bp_NAMEPLAYER_J1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bp_NAMEPLAYER_J2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bp_IA1_Level_3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(bp_IA2_Level_1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bp_IA2_Level_2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bp_IA2_Level_3)
                        .addGap(3, 3, 3))
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bp_GAMEMODE_IA_PLAYER)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bp_NAMEPLAYER_J3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bp_IA3_Level_1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bp_IA3_Level_2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bp_IA3_Level_3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(67, 67, 67)
                .addComponent(bp_START, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 817, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 444, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.window.getContentPane());
        this.window.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        this.window.pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bp_STARTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bp_STARTActionPerformed      
        Jeu partie;
        int type_joueur1=-1;
        int type_joueur2=-1;
        /*
         * modeDeJeu 0 : joueur contre joueur
         * modeDeJeu 1 : IA contre joueur
         * modeDeJeu 2 : IA contre IA
         */
        if (this.bp_GAMEMODE_IA_IA.isSelected()){
            if (this.bp_IA1_Level_1.isSelected()){
            	type_joueur1 = 0;
            }
            else if (this.bp_IA1_Level_2.isSelected()){
            	type_joueur1 = 1;
            }
            else if (this.bp_IA1_Level_3.isSelected()){
            	type_joueur1 = 2;
            }
            if (this.bp_IA2_Level_1.isSelected()){
            	type_joueur2 = 0;
            }
            else if (this.bp_IA2_Level_2.isSelected()){
            	type_joueur2 = 1;
            }
            else if (this.bp_IA2_Level_3.isSelected()){
            	type_joueur2 = 2;
            }
           this.partie.modeDeJeu=2;
           this.partie.nomJoueur1="Ordinateur 1";
           this.partie.nomJoueur2="Ordinateur 2";
           this.partie.difficulteIA1=type_joueur1;
           this.partie.difficulteIA2=type_joueur2;
        }
        else if(this.bp_GAMEMODE_IA_PLAYER.isSelected()){
            if (this.bp_IA3_Level_1.isSelected()){
            	type_joueur1 = 0;
            }
            else if (this.bp_IA3_Level_2.isSelected()){
            	type_joueur1 = 1;
            }
            else if (this.bp_IA3_Level_3.isSelected()){
            	type_joueur1 = 2;
            }
            this.partie.modeDeJeu=1;
            this.partie.nomJoueur1=bp_NAMEPLAYER_J3.getText();
            this.partie.nomJoueur2="Ordinateur";
            this.partie.difficulteIA2=type_joueur1;
        }
        else if(this.bp_GAMEMODE_PLAYER_PLAYER.isSelected()){
        	this.partie.nomJoueur1=bp_NAMEPLAYER_J1.getText();
            this.partie.nomJoueur2=bp_NAMEPLAYER_J2.getText();
        	this.partie.modeDeJeu=0;
        }
        this.partie.paramCharges=true;
        /* Partie de la gaufre a retranscire Bouton lancer la partie

        visuel.setgaufre(partie.g);
        visuel.jPanel1.setSize(visuel.jPanel1.getWidth() -(visuel.jPanel1.getWidth()%4) ,visuel.jPanel1.getHeight() - (visuel.jPanel1.getWidth()%4));

        visuel.topaint = true;

        visuel.jPanel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {

                visuel.changemap = true;
                visuel.poschange = me.getPoint();
                visuel.topaint = true;
                changemap(visuel.poschange);

                visuel.repaint();
            }

            public void changemap(Point point) {
                int uniteX = visuel.jPanel1.getWidth()/visuel.COLONNE_GRILLE;
                int uniteY = visuel.jPanel1.getHeight()/visuel.LIGNE_GRILLE;
                int localx = point.x/uniteX;
                int localy = point.y/uniteY;
                partie.g.croquer(localx, localy,visuel.jcourant);

                visuel.gaufre.croquer(localx, localy);
                if (visuel.partie.joueur_courant == 1) {
                    visuel.partie.joueur_courant = 2;
                } else {
                    visuel.partie.joueur_courant = 1;
                }
                if(visuel.partie.g.estMangePoison()) {
                    visuel.endgame = true;
                }

            }
        });
        visuel.addComponentListener( new ComponentAdapter() {
            @Override
            public void componentResized( ComponentEvent e ) {
                visuel.repaint();
            }
        } );

        window.dispose();
        visuel.pack();

        visuel.setVisible(true);

        if(window.jRadioButton2.isSelected() || window.jRadioButton3.isSelected()) {
            visuel.setpartie(partie);
            if(window.jRadioButton3.isSelected()) {
                visuel.j1 = window.jTextField3.getText();
                visuel.j2 = "IA ";
                visuel.vsIA = true;
            }
            else {
                visuel.j1 = "IA 1";
                visuel.j2 = "IA 2";
            }
            Timer timer = new Timer(400 ,visuel.lancerjeu);
            timer.setRepeats(true);
            visuel.topaint = true;
            visuel.repaint();
            timer.start();

        }
        else {
            visuel.setpartie(partie);
            visuel.j1 = window.jTextField2.getText();
            visuel.j2 = window.jTextField3.getText();
        }
    }//GEN-LAST:event_bp_STARTActionPerformed
    */
    }
    
    private void enable_bp(){
        if ((bp_GAMEMODE_PLAYER_PLAYER.isSelected()== false) && (bp_GAMEMODE_IA_PLAYER.isSelected()== false) && (bp_GAMEMODE_IA_IA.isSelected()== false)){
            bp_IA1_Level_1.setEnabled(false);
            bp_IA1_Level_2.setEnabled(false);
            bp_IA1_Level_3.setEnabled(false);
            bp_IA2_Level_1.setEnabled(false);
            bp_IA2_Level_2.setEnabled(false);
            bp_IA2_Level_3.setEnabled(false);
            bp_IA3_Level_1.setEnabled(false);
            bp_IA3_Level_2.setEnabled(false);
            bp_IA3_Level_3.setEnabled(false);
            bp_NAMEPLAYER_J3.setEnabled(false);
            bp_NAMEPLAYER_J1.setEnabled(false);
            bp_NAMEPLAYER_J2.setEnabled(false);
        }
    }
    
    private void bp_NAMEPLAYER_J2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bp_NAMEPLAYER_J2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bp_NAMEPLAYER_J2ActionPerformed

    private void bp_NAMEPLAYER_J1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bp_NAMEPLAYER_J1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bp_NAMEPLAYER_J1ActionPerformed

    private void bp_GAMEMODE_PLAYER_PLAYERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bp_GAMEMODE_PLAYER_PLAYERActionPerformed
        this.bp_NAMEPLAYER_J3.setEnabled(false);
        this.bp_IA1_Level_1.setEnabled(false);
        this.bp_IA1_Level_2.setEnabled(false);
        this.bp_IA1_Level_3.setEnabled(false);
        this.bp_IA2_Level_1.setEnabled(false);
        this.bp_IA2_Level_2.setEnabled(false);
        this.bp_IA2_Level_3.setEnabled(false);
        this.bp_IA3_Level_1.setEnabled(false);
        this.bp_IA3_Level_2.setEnabled(false);
        this.bp_IA3_Level_3.setEnabled(false);
        this.bp_GAMEMODE_IA_IA.setSelected(false);
        this.bp_GAMEMODE_IA_PLAYER.setSelected(false);
        this.bp_IA1_Level_1.setSelected(false);
        this.bp_IA1_Level_2.setSelected(false);
        this.bp_IA1_Level_3.setSelected(false);
        this.bp_IA2_Level_1.setSelected(false);
        this.bp_IA2_Level_2.setSelected(false);
        this.bp_IA2_Level_3.setSelected(false);
        this.bp_IA3_Level_1.setSelected(false);
        this.bp_IA3_Level_2.setSelected(false);
        this.bp_IA3_Level_3.setSelected(false);
        this.bp_NAMEPLAYER_J1.setEnabled(true);
        this.bp_NAMEPLAYER_J2.setEnabled(true);
        this.bp_NAMEPLAYER_J3.setText("NomJoueur");
    }//GEN-LAST:event_bp_GAMEMODE_PLAYER_PLAYERActionPerformed

    private void bp_NAMEPLAYER_J3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bp_NAMEPLAYER_J3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bp_NAMEPLAYER_J3ActionPerformed

    private void bp_GAMEMODE_IA_PLAYERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bp_GAMEMODE_IA_PLAYERActionPerformed
        this.bp_NAMEPLAYER_J1.setEnabled(false);
        this.bp_NAMEPLAYER_J2.setEnabled(false);
        this.bp_IA1_Level_1.setEnabled(false);
        this.bp_IA1_Level_2.setEnabled(false);
        this.bp_IA1_Level_3.setEnabled(false);
        this.bp_IA2_Level_1.setEnabled(false);
        this.bp_IA2_Level_2.setEnabled(false);
        this.bp_IA2_Level_3.setEnabled(false);
        this.bp_GAMEMODE_PLAYER_PLAYER.setSelected(false);
        this.bp_GAMEMODE_IA_IA.setSelected(false);
        this.bp_IA1_Level_1.setSelected(false);
        this.bp_IA1_Level_2.setSelected(false);
        this.bp_IA1_Level_3.setSelected(false);
        this.bp_IA2_Level_1.setSelected(false);
        this.bp_IA2_Level_2.setSelected(false);
        this.bp_IA2_Level_3.setSelected(false);
        this.bp_IA3_Level_1.setEnabled(true);
        this.bp_IA3_Level_2.setEnabled(true);
        this.bp_IA3_Level_3.setEnabled(true);
        this.bp_NAMEPLAYER_J3.setEnabled(true);
        this.bp_NAMEPLAYER_J1.setText("NomJoueur1");
        this.bp_NAMEPLAYER_J2.setText("NomJoueur2");
    }//GEN-LAST:event_bp_GAMEMODE_IA_PLAYERActionPerformed

    private void bp_GAMEMODE_IA_IAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bp_GAMEMODE_IA_IAActionPerformed
        this.bp_NAMEPLAYER_J1.setEnabled(false);
        this.bp_NAMEPLAYER_J2.setEnabled(false);
        this.bp_NAMEPLAYER_J3.setEnabled(false);
        this.bp_IA3_Level_1.setEnabled(false);
        this.bp_IA3_Level_2.setEnabled(false);
        this.bp_IA3_Level_3.setEnabled(false);
        this.bp_GAMEMODE_IA_PLAYER.setSelected(false);
        this.bp_GAMEMODE_PLAYER_PLAYER.setSelected(false);
        this.bp_IA3_Level_1.setSelected(false);
        this.bp_IA3_Level_2.setSelected(false);
        this.bp_IA3_Level_3.setSelected(false);
        this.bp_IA1_Level_1.setEnabled(true);
        this.bp_IA1_Level_2.setEnabled(true);
        this.bp_IA1_Level_3.setEnabled(true);
        this.bp_IA2_Level_1.setEnabled(true);
        this.bp_IA2_Level_2.setEnabled(true);
        this.bp_IA2_Level_3.setEnabled(true);
        this.bp_NAMEPLAYER_J1.setText("NomJoueur1");
        this.bp_NAMEPLAYER_J2.setText("NomJoueur2");
        this.bp_NAMEPLAYER_J3.setText("NomJoueur");
    }//GEN-LAST:event_bp_GAMEMODE_IA_IAActionPerformed

    private void bp_NAMEPLAYER_J1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bp_NAMEPLAYER_J1MouseClicked
    	this.bp_NAMEPLAYER_J1.setText("");
    }//GEN-LAST:event_bp_NAMEPLAYER_J1MouseClicked

    private void bp_NAMEPLAYER_J2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bp_NAMEPLAYER_J2MouseClicked
    	this.bp_NAMEPLAYER_J2.setText("");
    }//GEN-LAST:event_bp_NAMEPLAYER_J2MouseClicked

    private void bp_NAMEPLAYER_J3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bp_NAMEPLAYER_J3MouseClicked
    	this.bp_NAMEPLAYER_J3.setText("");
    }//GEN-LAST:event_bp_NAMEPLAYER_J3MouseClicked

    private void bp_IA1_Level_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bp_IA1_Level_1ActionPerformed
    	this.bp_IA1_Level_2.setSelected(false);
        this.bp_IA1_Level_3.setSelected(false);
    }//GEN-LAST:event_bp_IA1_Level_1ActionPerformed
    
    private void bp_IA3_Level_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bp_IA3_Level_1ActionPerformed
        this.bp_IA3_Level_2.setSelected(false);
        this.bp_IA3_Level_3.setSelected(false);
    }//GEN-LAST:event_bp_IA3_Level_1ActionPerformed

    private void bp_IA2_Level_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bp_IA2_Level_1ActionPerformed
        this.bp_IA2_Level_2.setSelected(false);
        this.bp_IA2_Level_3.setSelected(false);
    }//GEN-LAST:event_bp_IA2_Level_1ActionPerformed

    private void bp_IA1_Level_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bp_IA1_Level_2ActionPerformed
        this.bp_IA1_Level_1.setSelected(false);
        this.bp_IA1_Level_3.setSelected(false);
    }//GEN-LAST:event_bp_IA1_Level_2ActionPerformed

    private void bp_IA1_Level_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bp_IA1_Level_3ActionPerformed
        this.bp_IA1_Level_1.setSelected(false);
        this.bp_IA1_Level_2.setSelected(false);
    }//GEN-LAST:event_bp_IA1_Level_3ActionPerformed

    private void bp_IA2_Level_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bp_IA2_Level_2ActionPerformed
        this.bp_IA2_Level_1.setSelected(false);
        this.bp_IA2_Level_3.setSelected(false);
    }//GEN-LAST:event_bp_IA2_Level_2ActionPerformed
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(New_game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(New_game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(New_game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(New_game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bp_GAMEMODE_IA_IA;
    private javax.swing.JRadioButton bp_GAMEMODE_IA_PLAYER;
    private javax.swing.JRadioButton bp_GAMEMODE_PLAYER_PLAYER;
    private javax.swing.JRadioButton bp_IA1_Level_1;
    private javax.swing.JRadioButton bp_IA1_Level_2;
    private javax.swing.JRadioButton bp_IA1_Level_3;
    private javax.swing.JRadioButton bp_IA2_Level_1;
    private javax.swing.JRadioButton bp_IA2_Level_2;
    private javax.swing.JRadioButton bp_IA2_Level_3;
    private javax.swing.JRadioButton bp_IA3_Level_1;
    private javax.swing.JRadioButton bp_IA3_Level_2;
    private javax.swing.JRadioButton bp_IA3_Level_3;
    public javax.swing.JTextField bp_NAMEPLAYER_J1;
    public javax.swing.JTextField bp_NAMEPLAYER_J2;
    public javax.swing.JTextField bp_NAMEPLAYER_J3;
    private javax.swing.JButton bp_START;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
