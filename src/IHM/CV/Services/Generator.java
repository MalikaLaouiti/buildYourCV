package IHM.CV.Services;

import IHM.CV.Services.Data;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Generator {

    public File generateHTML(Data data, String outputPath) throws IOException {
        String html = buildHTML(data);

        File file = new File(outputPath);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(html);
        }

        return file;
    }

    private String buildHTML(Data data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateNaissance = data.getDateNaissance() != null ?
                sdf.format(data.getDateNaissance()) : "Non spécifiée";

        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>\n");
        html.append("<html lang=\"fr\">\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        html.append("    <title>CV - ").append(data.getNom()).append(" ").append(data.getPrenom()).append("</title>\n");
        html.append("    <style>\n");
        html.append(getCSS());
        html.append("    </style>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("    <div class=\"cv-container\">\n");
        html.append(generateHeader(data));
        html.append(generatePersonalInfo(data, dateNaissance));
        html.append(generateSkills(data));
        html.append(generateExperience(data));
        html.append(generateEducation(data));
        html.append(generateProject(data));
        html.append("    </div>\n");
        html.append("</body>\n");
        html.append("</html>");

        return html.toString();
    }

    private String generateHeader(Data data) {
        StringBuilder sb = new StringBuilder();
        sb.append("        <div class=\"header\">\n");
        sb.append("            <h1 class=\"name\">").append(data.getPrenom()).append(" ").append(data.getNom()).append("</h1>\n");
        sb.append("            <div class=\"contact-info\">\n");
        sb.append("                <div class=\"contact-item\">📧 ").append(escapeHtml(data.getEmail())).append("</div>\n");
        sb.append("                <div class=\"contact-item\">📱 ").append(escapeHtml(data.getTelephone())).append("</div>\n");
        sb.append("                <div class=\"contact-item\">📍 ").append(escapeHtml(data.getAdresse())).append("</div>\n");
        sb.append("                <div class=\"contact-item\">🐱 ").append(escapeHtml(data.getGithub())).append("</div>\n");
        sb.append("            </div>\n");
        sb.append("        </div>\n");
        return sb.toString();
    }

    private String generatePersonalInfo(Data data, String dateNaissance) {
        StringBuilder sb = new StringBuilder();
        sb.append("        <div class=\"section\">\n");
        sb.append("            <h2 class=\"section-title\">INFORMATIONS PERSONNELLES</h2>\n");
        sb.append("            <div class=\"grid-2col\">\n");
        sb.append("                <div class=\"info-group\">\n");
        sb.append("                    <div class=\"info-label\">Date de naissance</div>\n");
        sb.append("                    <div class=\"info-value\">").append(dateNaissance).append("</div>\n");
        sb.append("                </div>\n");
        sb.append("                <div class=\"info-group\">\n");
        sb.append("                    <div class=\"info-label\">Sexe</div>\n");
        sb.append("                    <div class=\"info-value\">").append(data.getSexe()).append("</div>\n");
        sb.append("                </div>\n");
        sb.append("            </div>\n");
        sb.append("        </div>\n");
        return sb.toString();
    }

    private String generateSkills(Data data) {
        StringBuilder sb = new StringBuilder();
        sb.append("        <div class=\"section\">\n");
        sb.append("            <h2 class=\"section-title\">COMPÉTENCES TECHNIQUES</h2>\n");
        sb.append("            <div class=\"grid-2col\">\n");
        sb.append("                <div>\n");
        sb.append("                    <div class=\"info-group\">\n");
        sb.append("                        <div class=\"info-label\">Langues parlées</div>\n");
        sb.append("                        <div class=\"info-value\">").append(String.join(", ", data.getLangues())).append("</div>\n");
        sb.append("                    </div>\n");
        sb.append("                    <div class=\"info-group\">\n");
        sb.append("                        <div class=\"info-label\">Compétences</div>\n");
        sb.append("                        <ul class=\"skills-list\">\n");
        sb.append(generateSkillItems(data.getCompetences()));
        sb.append("                        </ul>\n");
        sb.append("                    </div>\n");
        sb.append("                </div>\n");
        sb.append("                <div>\n");
        sb.append("                    <div class=\"info-group\">\n");
        sb.append("                        <div class=\"info-label\">Langages de programmation</div>\n");
        sb.append("                        <ul class=\"skills-list\">\n");
        sb.append(generateSkillItems(data.getLangages()));
        sb.append("                        </ul>\n");
        sb.append("                    </div>\n");
        sb.append("                    <div class=\"info-group\">\n");
        sb.append("                        <div class=\"info-label\">Bases de données</div>\n");
        sb.append("                        <ul class=\"skills-list\">\n");
        sb.append(generateSkillItems(data.getDatabases()));
        sb.append("                        </ul>\n");
        sb.append("                    </div>\n");
        sb.append("                    <div class=\"info-group\">\n");
        sb.append("                        <div class=\"info-label\">Outils & Technologies</div>\n");
        sb.append("                        <ul class=\"skills-list\">\n");
        sb.append(generateSkillItems(data.getOutils()));
        sb.append("                        </ul>\n");
        sb.append("                    </div>\n");
        sb.append("                </div>\n");
        sb.append("            </div>\n");
        sb.append("        </div>\n");
        return sb.toString();
    }

    private String generateSkillItems(List<String> skills) {
        if (skills == null || skills.isEmpty()) {
            return "                            <li class='skill-item'>Aucune sélection</li>\n";
        }

        StringBuilder sb = new StringBuilder();
        for (String skill : skills) {
            sb.append("                            <li class='skill-item'>")
                    .append(escapeHtml(skill))
                    .append("</li>\n");
        }
        return sb.toString();
    }

    private String generateExperience(Data data) {
        StringBuilder sb = new StringBuilder();
        sb.append("        <div class=\"section\">\n");
        sb.append("            <h2 class=\"section-title\">EXPÉRIENCE PROFESSIONNELLE</h2>\n");

        String experience = data.getExperience();
        String[] experiences = experience.split("\n");
        for (String exp : experiences) {
            if (!exp.trim().isEmpty()) {
                sb.append("            <div class=\"experience-item\">\n");
                sb.append("                <div class=\"item-description\">")
                        .append(escapeHtml(exp))
                        .append("</div>\n");
                sb.append("            </div>\n");
            }
        }


        sb.append("        </div>\n");
        return sb.toString();
    }

//    private String getDefaultExperience() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("            <div class=\"experience-item\">\n");
//        sb.append("                <div class=\"item-title\">Développeur FullStack (Projet de Fin d'Études)</div>\n");
//        sb.append("                <div class=\"item-date\">Février 2025 - Présent</div>\n");
//        sb.append("                <div class=\"item-description\">\n");
//        sb.append("                    Conception et implémentation d'un langage de programmation FullStack personnalisé pour le web.\n");
//        sb.append("                </div>\n");
//        sb.append("            </div>\n");
//        return sb.toString();
//    }

    private String generateEducation(Data data) {
        StringBuilder sb = new StringBuilder();
        sb.append("        <div class=\"section\">\n");
        sb.append("            <h2 class=\"section-title\">FORMATION</h2>\n");

        String formation = data.getFormation();
        String[] educations = formation.split("\n");
        for (String edu : educations) {
            if (!edu.trim().isEmpty()) {
                sb.append("            <div class=\"education-item\">\n");
                sb.append("                <div class=\"item-description\">")
                        .append(escapeHtml(edu))
                        .append("</div>\n");
                sb.append("            </div>\n");
            }
        }


        sb.append("        </div>\n");
        return sb.toString();
    }

//    private String getDefaultEducation() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("            <div class=\"education-item\">\n");
//        sb.append("                <div class=\"item-title\">Licence en Génie Logiciel</div>\n");
//        sb.append("                <div class=\"item-date\">Juin 2025</div>\n");
//        sb.append("                <div class=\"item-description\">\n");
//        sb.append("                    Institut Supérieur d'Informatique et de Mathématiques de Monastir (ISIMM)\n");
//        sb.append("                </div>\n");
//        sb.append("            </div>\n");
//        return sb.toString();
//    }

    private String generateProject(Data data) {
        StringBuilder sb = new StringBuilder();
        sb.append("        <div class=\"section\">\n");
        sb.append("            <h2 class=\"section-title\">PROJETS</h2>\n");

        String projet = data.getProjet();
        String[] projects = projet.split("\n");
        for (String proj : projects) {
            if (!proj.trim().isEmpty()) {
                sb.append("            <div class=\"experience-item\">\n");
                sb.append("                <div class=\"item-description\">")
                        .append(escapeHtml(proj))
                        .append("</div>\n");
                sb.append("            </div>\n");
            }
        }


        sb.append("        </div>\n");
        return sb.toString();
    }

//    private String getDefaultProject() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("            <div class=\"experience-item\">\n");
//        sb.append("                <div class=\"item-title\">Développement d'un Langage de Programmation FullStack</div>\n");
//        sb.append("                <div class=\"item-description\">\n");
//        sb.append("                    Conception et implémentation complète d'un langage de programmation FullStack pour le web.\n");
//        sb.append("                </div>\n");
//        sb.append("            </div>\n");
//        return sb.toString();
//    }

    private String getCSS() {
        StringBuilder css = new StringBuilder();
        css.append("        * {\n");
        css.append("            margin: 0;\n");
        css.append("            padding: 0;\n");
        css.append("            box-sizing: border-box;\n");
        css.append("            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n");
        css.append("        }\n");
        css.append("        \n");
        css.append("        body {\n");
        css.append("            background-color: #f5f5f5;\n");
        css.append("            color: #333;\n");
        css.append("            line-height: 1.6;\n");
        css.append("            padding: 20px;\n");
        css.append("        }\n");
        css.append("        \n");
        css.append("        .cv-container {\n");
        css.append("            max-width: 1000px;\n");
        css.append("            margin: 0 auto;\n");
        css.append("            background: white;\n");
        css.append("            box-shadow: 0 0 20px rgba(0,0,0,0.1);\n");
        css.append("            border-radius: 10px;\n");
        css.append("            overflow: hidden;\n");
        css.append("        }\n");
        css.append("        \n");
        css.append("        .header {\n");
        css.append("            background: linear-gradient(135deg, #2c3e50 0%, #3498db 100%);\n");
        css.append("            color: white;\n");
        css.append("            padding: 40px;\n");
        css.append("            text-align: center;\n");
        css.append("        }\n");
        css.append("        \n");
        css.append("        .name {\n");
        css.append("            font-size: 2.5em;\n");
        css.append("            font-weight: 700;\n");
        css.append("            margin-bottom: 10px;\n");
        css.append("        }\n");
        css.append("        \n");
        css.append("        .contact-info {\n");
        css.append("            display: flex;\n");
        css.append("            justify-content: center;\n");
        css.append("            flex-wrap: wrap;\n");
        css.append("            gap: 20px;\n");
        css.append("            margin-top: 20px;\n");
        css.append("            font-size: 0.95em;\n");
        css.append("        }\n");
        css.append("        \n");
        css.append("        .section {\n");
        css.append("            padding: 30px 40px;\n");
        css.append("            border-bottom: 1px solid #eee;\n");
        css.append("        }\n");
        css.append("        \n");
        css.append("        .section-title {\n");
        css.append("            color: #2c3e50;\n");
        css.append("            font-size: 1.4em;\n");
        css.append("            margin-bottom: 20px;\n");
        css.append("            padding-bottom: 10px;\n");
        css.append("            border-bottom: 2px solid #3498db;\n");
        css.append("            font-weight: 600;\n");
        css.append("        }\n");
        css.append("        \n");
        css.append("        .grid-2col {\n");
        css.append("            display: grid;\n");
        css.append("            grid-template-columns: repeat(2, 1fr);\n");
        css.append("            gap: 30px;\n");
        css.append("        }\n");
        css.append("        \n");
        css.append("        .skills-list {\n");
        css.append("            list-style: none;\n");
        css.append("            display: flex;\n");
        css.append("            flex-wrap: wrap;\n");
        css.append("            gap: 10px;\n");
        css.append("        }\n");
        css.append("        \n");
        css.append("        .skill-item {\n");
        css.append("            background: #e8f4fc;\n");
        css.append("            padding: 8px 15px;\n");
        css.append("            border-radius: 20px;\n");
        css.append("            font-size: 0.9em;\n");
        css.append("            color: #2c3e50;\n");
        css.append("            border: 1px solid #3498db;\n");
        css.append("        }\n");
        css.append("        \n");
        css.append("        @media print {\n");
        css.append("            body { background: white; padding: 0; }\n");
        css.append("            .cv-container { box-shadow: none; border-radius: 0; }\n");
        css.append("        }\n");
        return css.toString();
    }

    private String escapeHtml(String text) {
        if (text == null || text.isEmpty()) return "";
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}