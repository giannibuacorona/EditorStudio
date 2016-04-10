/**
 * <p>
 * 
 * 
 * Game Content è la descrizione del nostro modello. Il modello è basato su
 * box2d. Comprende: body, joint, fixture e shape.
 * 
 * 
 * Modifiche al modello<br/>
 * Di struttura: aggiunta o rimozioni di oggetti (create e destroy)<br/>
 * Di dati: per mezzo di un metodo setter<br/>
 * * Per ogni modifica viene generato un evento specifico. L'evento contiene il
 * reference all'oggetto che ha subito la modifica.
 * 
 * Eventi di struttura <br/>
 * Creazione: generato da un'istanza di GameContent <br/>
 * Distruzione: generato da un'istanza di GameContent e dall'oggetto distrutto
 * 
 * 
 * Eventi di modifica dati<br/>
 * Generati dall'oggetto che subisce la modifica
 * 
 * Ascoltatori<br/>
 * Presso ogni oggetto e presso il game content si possono registrare più
 * ascoltatori.
 * 
 * Due stati degli oggetti: GET_ONLY e GET_SET<br/>
 * Durante una notifica agli ascoltatori (fire-event) gli oggetti del game
 * content sono in stato GET_ONLY. Non è possibile effetgtuare nessuna modifica
 * al modello, ne di dati ne strutturale. Alla fine del loop di notifica gli
 * oggetti sono in stato GET_SET. Si può effettuare una modifica al modello.
 * 
 * Struttura
 * 
 * DBody <2---n> DJoint <br/>
 * DBody <1---n> DFixture <br/>
 * DFixture <1---1> DShape
 * 
 * 
 * 
 * 
 * </p>
 */
/**
 * @author gianni
 *
 */
package gamecontent;