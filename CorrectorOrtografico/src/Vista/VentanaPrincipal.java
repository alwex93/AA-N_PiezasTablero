package Vista;

import Controlador.ControlerInterface;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class VentanaPrincipal extends JFrame{
    private JButton marcarButton;
    private JButton sustituirButton;
    private JButton personalizadaButton;
    private JEditorPane texto;
    private JPanel mainPanel;
    private JLabel test;
    private ControlerInterface controlador;
    private Template temp;
    private VentanaSustitucion avanzadas;

    private final String PATH_TEMPLATES = "recursos";
    private final String TEMPLATE = "template.ftl";

    private final boolean SUSTITUIR = true;
    private final boolean MARCAR = false;

    public VentanaPrincipal() {
        init();
    }

    public VentanaPrincipal(ControlerInterface control) {
        init();
        controlador = control;
    }

    private void init(){
        setTitle("Corrector Ortográfico");
        setSize(640, 480);
        setTemplateConfigure();
        texto.setContentType("text/html");
        addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                texto.requestFocus();
            }
        });
        setTemplateConfigure();
        marcarButton.addActionListener(e -> {
            alterarTexto(MARCAR);
            int dot = texto.getCaret().getDot();
            String docText = null;
            try {
                docText = texto.getDocument().getText(0, texto.getDocument().getLength());
                String nextCharacter = docText.substring(dot);
                test.setText(nextCharacter);
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        });
        sustituirButton.addActionListener(e -> {
            alterarTexto(SUSTITUIR);
            Point p = texto.getCaret().getMagicCaretPosition();
            if (p!= null) test.setText("x: " + p.x + " y: " + p.y);
        });
        personalizadaButton.addActionListener(e -> {
            avanzadas = VentanaSustitucion.getWindow(this, getTexto(), getTest(getTexto()));
            if (avanzadas != null && !avanzadas.isVisible()){
                avanzadas = new VentanaSustitucion(this,getTexto(), getTest(getTexto()));
            }
        });
        add(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setTemplateConfigure(){
        try {
            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(PATH_TEMPLATES));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            temp = cfg.getTemplate(TEMPLATE);
        } catch (IOException e){
            System.out.println("Error");
        }
    }

    private void alterarTexto(boolean sustMarc){
        StringBuilder textoMarcado = new StringBuilder();
        String texto = getTexto();
        int punteroTexto = 0;
        for(Palabra pal : getTest(texto)){
            textoMarcado.append(texto, punteroTexto, pal.getInitPos());
            if (sustMarc){
                textoMarcado.append(alterarPalabra(pal, null));
            } else {
                textoMarcado.append(alterarPalabra(pal, texto));
            }
            punteroTexto = pal.getEndPos();
        }
        textoMarcado.append(texto.substring(punteroTexto));
        this.texto.setText(getTemplate(textoMarcado.toString()));
    }

    private String alterarPalabra(Palabra pal, String texto){
        if (texto != null){//Marcado
            return "<b color='red'><s>" + texto.substring(pal.getInitPos(), pal.getEndPos()) + "<s></b>";
        } else {//Sustituir
            return "<b color='blue'>" + pal.getFirstSustituto() + "</b>";
        }
    }

    private String getTexto(){
        Element seccion = Jsoup.parse(texto.getText()).select("p").first();
        if (seccion == null){
            seccion = Jsoup.parse(texto.getText()).select("body").first();
        }
        return seccion.text();
    }

    private Palabra[] getTest(String texto){
        String[] palabras = texto.split(" ");
        Palabra[] ret = new Palabra[palabras.length/2 + palabras.length%2];
        for (int n = 0, p = 0, punt = 0, init; n < palabras.length; n++){
            if (n%2 != 1){
                init = texto.indexOf(palabras[n], punt);
                ret[p] = new Palabra(palabras[n], init, init + palabras[n].length(), new String[]{palabras[n] + "1"});
                punt = ret[p].getEndPos();
                p++;
            }
        }
        return ret;
    }

    private Palabra[] getPalabras(String texto){
        return controlador.comprobar(texto);
    }

    private String getTemplate(String texto){
        try {
            /* Get the template (uses cache internally) */
            Map<String, Object> root = new HashMap<>();
            root.put("text", texto);
            /* Merge data-model with template */
            Writer out = new StringWriter();
            temp.process(root, out);
            return out.toString();
        } catch (IOException | TemplateException e){
            return "Error." + e.getMessage();
        }
    }

    public void setText(String text){
        texto.setText(text);
    }

}
