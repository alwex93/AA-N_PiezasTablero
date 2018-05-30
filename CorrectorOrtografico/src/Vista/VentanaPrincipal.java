package Vista;

import Controlador.ControlerInterface;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class VentanaPrincipal {
    private JButton marcarButton;
    private JButton sustituirButton;
    private JButton personalizadaButton;
    private JEditorPane texto;
    private ControlerInterface controlador;
    private Configuration cfg;

    public VentanaPrincipal(ControlerInterface control) {
        controlador = control;
        setTemplateConfigure();
        marcarButton.addActionListener(e -> marcarPalabras());
        sustituirButton.addActionListener(e -> sustituirPalabras());
        personalizadaButton.addActionListener(e -> {

        });
    }

    private void setTemplateConfigure(){
        try {
            cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File("Vista"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        } catch (IOException e){
            System.out.println("Error");
        }
    }

    private void sustituirPalabras(){
        StringBuilder textoMarcado = new StringBuilder();
        String texto = this.texto.getText();
        int punteroTexto = 0;
        for(Palabra pal : getPalabras(texto)){
            textoMarcado.append(texto.substring(punteroTexto, pal.getInitPos() - 1))
                    .append("<a>").append(pal.getSustituto()).append("</a>");
            punteroTexto = pal.getEndPos() + 1;
        }
        textoMarcado.append(texto.substring(punteroTexto));
        this.texto.setText(getTemplate(textoMarcado.toString(), "templateSustituir.ftl"));
    }

    private void marcarPalabras(){
        StringBuilder textoMarcado = new StringBuilder();
        String texto = this.texto.getText();
        int punteroTexto = 0;
        for(Palabra pal : getPalabras(texto)){
            textoMarcado.append(texto.substring(punteroTexto, pal.getInitPos() - 1))
                        .append("<a>").append(texto.substring(pal.getInitPos(), pal.getEndPos())).append("</a>");
            punteroTexto = pal.getEndPos() + 1;
        }
        textoMarcado.append(texto.substring(punteroTexto));
        this.texto.setText(getTemplate(textoMarcado.toString(), "templateMarcar.ftl"));
    }

    private Palabra[] getPalabras(String texto){
        return controlador.comprobar(texto);
    }

    private String getTemplate(String texto, String template){
        try {
            /* Get the template (uses cache internally) */
            Map<String, Object> root = new HashMap<>();
            root.put("text", texto);
            /* Merge data-model with template */
            Writer out = new StringWriter();
            Template temp = cfg.getTemplate(template);
            temp.process(root, out);
            return out.toString();
        } catch (IOException | TemplateException e){
            return "Error." + e.getMessage();
        }
    }
}
