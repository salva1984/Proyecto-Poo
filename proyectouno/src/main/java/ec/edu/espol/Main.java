package ec.edu.espol;

import java.util.Random;
import java.util.Scanner;
import java.util.Iterator;

import ec.edu.espol.cartas.Carta;
import ec.edu.espol.cartas.CartaComodin;
import ec.edu.espol.cartas.CartaNormal;
import ec.edu.espol.cartas.Color;
import ec.edu.espol.cartas.TipoComodin;
import ec.edu.espol.jugadores.Jugador;

public class Main {
    public static void main(String[] args) {

        Random rd = new Random();

        Mazo m = new Mazo();

        Jugador jm = new Jugador(m.getCartas());
        Jugador jh = new Jugador(m.getCartas());

        // robamos una carta
        Carta uc = Juego.robarCarta(m.getCartas());
        boolean condicion = (!jm.getCartas().isEmpty()) && (!jh.getCartas().isEmpty());
        // Turno del jugador
        while (condicion) {
            if (condicion) {
                boolean turno = true;
                Scanner sc = new Scanner(System.in);
                while (turno) {
                    System.out.println("--------------------");
                    System.out.println("Cartas del rival: ");
                    System.out.println(jm.toString());
                    System.out.println("--------------------");
                    System.out.println("Tus cartas: ");
                    System.out.println(jh.toString());
                    System.out.println("--------------------");
                    System.out.println("Ultima carta");
                    System.out.println(uc);

                    int cont = 0;
                    for (Carta carta : jh.getCartas()) {
                        if (Juego.revisarCarta(uc, carta))
                            cont += 1;
                    }
                    if (cont == 0) {
                        System.out.println("--------------------");
                        System.out.println(
                                "No tienes ninguna carta valida para jugar, robando una carta y pasando turno");
                        System.out.println("--------------------");
                        jh.getCartas().add(Juego.robarCarta(m.getCartas()));
                        turno = false;
                    } else {
                        System.out.println("--------------------");
                        System.out.println("Ingrese el indice de la  carta que quieres jugar: ");
                        int idx = sc.nextInt();
                        sc.nextLine();
                        if (idx >= 0 && idx <= jh.getCartas().size() - 1) {
                            Carta cartaJugar = jh.getCartas().get(idx);
                            if (Juego.revisarCarta(uc, cartaJugar)) {

                                if (cartaJugar instanceof CartaNormal) {
                                    // Metemos al mazo la ultima carta
                                    m.getCartas().add(uc);
                                    // Cambiamos la ultima carta
                                    uc = cartaJugar;
                                    // quitmos la carta jugada al jugador
                                    jh.getCartas().remove(idx);
                                    // ponemos turno falso para terminar el while
                                    turno = false;
                                    System.out.println("Jugaste la carta: " + uc.toString());
                                    System.out.println("--------------------");
                                }

                                if (cartaJugar instanceof CartaComodin) {
                                    CartaComodin cc = (CartaComodin) cartaJugar;
                                    if (cc.getTipo() == TipoComodin.BLOQUEO || cc.getTipo() == TipoComodin.REVERSA) {
                                        // Metemos al mazo la ultima carta
                                        m.getCartas().add(uc);
                                        // Cambiamos la ultima carta
                                        uc = cc;
                                        // quitmos la carta jugada al jugador
                                        jh.getCartas().remove(idx);
                                        // como no hacemos turno false, juegas otra vez

                                        System.out.println("Jugaste la carta: " + uc.toString());
                                        System.out.println("Bloqueaste al rival, juegas de nuevo!");
                                        System.out.println("--------------------");
                                    }
                                    if (cc.getTipo() == TipoComodin.CAMBIOCOLOR) {
                                        System.out.println(
                                                "Ingresa que color quieres que tome la carta cambio de color: R(rojo),A(amarillo),Z(azul),V(verde)");
                                        String color = sc.nextLine();
                                        Color cl = Color.valueOf(color);
                                        cc.setColor(cl);
                                        m.getCartas().add(uc);
                                        uc = cc;
                                        jh.getCartas().remove(idx);
                                        System.out.println("Jugaste la carta: " + uc.toString());
                                        System.out.println("--------------------");

                                        turno = false;

                                    }
                                    if (cc.getTipo() == TipoComodin.MAS2) {
                                        if (cc.getColor() == Color.N) {
                                            System.out.println(
                                                    "Ingresa que color quieres que tome la carta negra: R(rojo),A(amarillo),Z(azul),V(verde)");
                                            String color = sc.nextLine();
                                            Color cl = Color.valueOf(color);
                                            cc.setColor(cl);
                                            m.getCartas().add(uc);
                                            uc = cc;
                                            jh.getCartas().remove(idx);
                                            System.out.println("Tu rival toma dos cartas!");
                                            System.out.println("Jugaste la carta: " + uc.toString());
                                            System.out.println("--------------------");
                                            jm.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            jm.getCartas().add(Juego.robarCarta(m.getCartas()));

                                            turno = false;
                                        } else {
                                            m.getCartas().add(uc);
                                            uc = cc;
                                            jh.getCartas().remove(idx);
                                            System.out.println("Tu rival toma dos cartas!");
                                            System.out.println("Jugaste la carta: " + uc.toString());
                                            System.out.println("--------------------");
                                            jm.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            jm.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            turno = false;
                                        }
                                    }
                                    if (cc.getTipo() == TipoComodin.MAS4) {
                                        if (cc.getColor() == Color.N) {
                                            System.out.println(
                                                    "Ingresa que color quieres que tome la carta negra: R(rojo),A(amarillo),Z(azul),V(verde)");
                                            String color = sc.nextLine();
                                            Color cl = Color.valueOf(color);
                                            cc.setColor(cl);
                                            m.getCartas().add(uc);
                                            uc = cc;
                                            jh.getCartas().remove(idx);
                                            System.out.println("Tu rival toma CUATRO cartas!");
                                            System.out.println("Jugaste la carta: " + uc.toString());
                                            System.out.println("--------------------");
                                            jm.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            jm.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            jm.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            jm.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            turno = false;
                                        } else {
                                            m.getCartas().add(uc);
                                            uc = cc;
                                            jh.getCartas().remove(idx);
                                            System.out.println("Tu rival toma CUATRO cartas!");
                                            System.out.println("Jugaste la carta: " + uc.toString());
                                            System.out.println("--------------------");
                                            jm.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            jm.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            jm.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            jm.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            turno = false;
                                        }
                                    }
                                }
                            } else {
                                System.out.println("Ingresa una carta que se pueda jugar!");
                                System.out.println("--------------------");
                            }
                        } else {
                            System.out.println("Ingrese un indice valido!");
                            System.out.println("--------------------");
                        }
                    }
                    if (jh.getCartas().size() == 1) {
                        System.out.println("--------------------");
                        System.out.println("UNO!");
                        System.out.println("--------------------");
                    }

                }
                if (jh.getCartas().isEmpty())
                    condicion = false;
                // TURNO DE LA MAQUINA
                boolean turnoMaquina = true;
                // Tenemos que usar iterator para eliminar cartas mientras las estamos usando o
                // da error

                while (turnoMaquina) {
                    Iterator<Carta> cartasMaquina = jm.getCartas().iterator();
                    while (cartasMaquina.hasNext()) {
                        Carta carta = cartasMaquina.next();
                        if (turnoMaquina) {
                            if (Juego.revisarCarta(uc, carta)) {
                                if (carta instanceof CartaNormal) {
                                    // Metemos al mazo la ultima carta
                                    m.getCartas().add(uc);
                                    // Cambiamos la ultima carta
                                    uc = carta;
                                    // quitmos la carta jugada al jugador
                                    cartasMaquina.remove();

                                    // ponemos turno falso para terminar el while

                                    System.out.println("La maquina jugó: " + uc.toString());
                                    turnoMaquina = false;
                                }
                                if (carta instanceof CartaComodin) {
                                    CartaComodin cc = (CartaComodin) carta;
                                    if (cc.getTipo() == TipoComodin.BLOQUEO || cc.getTipo() == TipoComodin.REVERSA) {
                                        // Metemos al mazo la ultima carta
                                        m.getCartas().add(uc);
                                        // Cambiamos la ultima carta
                                        uc = cc;
                                        // quitmos la carta jugada al jugador
                                        cartasMaquina.remove();
                                        // como no hacemos turno false, juegas otra vez

                                        System.out.println("La maquina jugó: " + uc.toString());
                                        System.out.println("Te bloquearon, la maquina juega de nuevo");
                                    }
                                    if (cc.getTipo() == TipoComodin.CAMBIOCOLOR) {

                                        Color cl = Color.values()[rd.nextInt(Color.values().length)];
                                        while (cl == Color.N) {
                                            cl = Color.values()[rd.nextInt(Color.values().length)];
                                        }
                                        cc.setColor(cl);
                                        m.getCartas().add(uc);
                                        uc = cc;
                                        cartasMaquina.remove();
                                        System.out.println("La maquina jugó: " + uc.toString());

                                        turnoMaquina = false;

                                    }
                                    if (cc.getTipo() == TipoComodin.MAS2) {
                                        if (cc.getColor() == Color.N) {
                                            Color cl = Color.values()[rd.nextInt(Color.values().length)];
                                            while (cl == Color.N) {
                                                cl = Color.values()[rd.nextInt(Color.values().length)];
                                            }
                                            cc.setColor(cl);
                                            m.getCartas().add(uc);
                                            uc = cc;
                                            cartasMaquina.remove();
                                            System.out.println("La maquina jugó: " + uc.toString());
                                            System.out.println("Tu tomas dos cartas!");
                                            jh.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            jh.getCartas().add(Juego.robarCarta(m.getCartas()));

                                            turnoMaquina = false;
                                        } else {
                                            m.getCartas().add(uc);
                                            uc = cc;
                                            cartasMaquina.remove();
                                            System.out.println("La maquina jugó: " + uc.toString());
                                            System.out.println("Tu tomas dos cartas!");
                                            jh.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            jh.getCartas().add(Juego.robarCarta(m.getCartas()));

                                            turnoMaquina = false;
                                        }
                                    }
                                    if (cc.getTipo() == TipoComodin.MAS4) {
                                        if (cc.getColor() == Color.N) {
                                            Color cl = Color.values()[rd.nextInt(Color.values().length)];
                                            while (cl == Color.N) {
                                                cl = Color.values()[rd.nextInt(Color.values().length)];
                                            }
                                            cc.setColor(cl);
                                            m.getCartas().add(uc);
                                            uc = cc;
                                            cartasMaquina.remove();
                                            System.out.println("La maquina jugó: " + uc.toString());
                                            System.out.println("Tu tomas CUATRO cartas!");
                                            jh.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            jh.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            jh.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            jh.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            turnoMaquina = false;
                                        } else {
                                            m.getCartas().add(uc);
                                            uc = cc;
                                            cartasMaquina.remove();
                                            System.out.println("La maquina jugó: " + uc.toString());
                                            System.out.println("Tu tomas CUATRO cartas!");
                                            jh.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            jh.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            jh.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            jh.getCartas().add(Juego.robarCarta(m.getCartas()));
                                            turnoMaquina = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (turnoMaquina) {
                        System.out.println("--------------------");
                        System.out.println("La maquina no jugó, robando carta");
                        jm.getCartas().add(Juego.robarCarta(m.getCartas()));
                    }
                    turnoMaquina = false;
                }
                if (jm.getCartas().size() == 1) {
                    System.out.println("--------------------");
                    System.out.println("UNO!");
                    System.out.println("--------------------");
                }
                if (jm.getCartas().isEmpty())
                    condicion = false;

            }
        }
        if (jh.getCartas().isEmpty())
            System.out.println("Ganaste!");
        else
            System.out.println("Perdiste, gano la maquina!");
    }

}
