package Vista;

import Controlador.Controler;
import Controlador.ControlerInterface;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class VentanaPrincipal extends JFrame implements ViewInterface{
    private JButton marcarButton;
    private JButton sustituirButton;
    private JButton personalizadaButton;
    private JEditorPane texto;
    private int cursor;
    private JPanel mainPanel;
    private ControlerInterface controler;
    private Template temp;
    private VentanaSustitucion avanzadas;
    private Palabra[] erroneas;
    private int pointer;

    private final String PATH_TEMPLATES = "recursos";
    private final String TEMPLATE = "template.ftl";

    private final boolean SUSTITUIR = true;
    private final boolean MARCAR = false;

    public VentanaPrincipal() {
        init();
        controler = new Controler();
        setVisible(false);
    }

    public void abrirVentana(){
        setVisible(true);
        new Thread(new Marcador(this, controler)).start();
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
            erroneas = controler.comprobar(getTexto());
            alterarTexto(MARCAR);
        });
        sustituirButton.addActionListener(e -> {
            if (erroneas == null){
                erroneas = controler.comprobar(getTexto());
            }
            alterarTexto(SUSTITUIR);
        });
        personalizadaButton.addActionListener(e -> {
            if (erroneas == null){
                erroneas = controler.comprobar(getTexto());
            }
            avanzadas = VentanaSustitucion.getWindow(this, getTexto(), erroneas);
            if (avanzadas != null && !avanzadas.isVisible()){
                avanzadas = new VentanaSustitucion(this,getTexto(), erroneas);
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
        for(Palabra pal : erroneas){
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
        try {
            this.texto.setCaretPosition(cursor + 1);
        } catch (IllegalArgumentException e){
            this.texto.setCaretPosition(texto.length() + 1);
        }
    }

    private String alterarPalabra(Palabra pal, String texto){
        if (texto != null){//Marcado
            return "<b color='red'><s>" + texto.substring(pal.getInitPos(), pal.getEndPos()) + "<s></b>";
        } else {//Sustituir
            return "<b color='blue'>" + pal.corregir(0) + "</b>";
        }
    }

    public String getTexto(){
        cursor = texto.getCaretPosition();
        Element seccion = Jsoup.parse(texto.getText()).select("p").first();
        if (seccion == null){
            seccion = Jsoup.parse(texto.getText()).select("body").first();
        }
        return seccion.text();
    }

    private String getTemplate(String texto){
        try {
            Map<String, Object> root = new HashMap<>();
            root.put("text", texto);
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

    public void marcarPalabras(Palabra[] palabras){
        erroneas = palabras;
        alterarTexto(MARCAR);
    }

}
