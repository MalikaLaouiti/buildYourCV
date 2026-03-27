package IHM.CV.Services;

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
        sb.append("                <span class=\"contact-item\">&#128231; ").append(escapeHtml(data.getEmail())).append("</span>\n");
        sb.append("                <span class=\"contact-item\">&#128241; ").append(escapeHtml(data.getTelephone())).append("</span>\n");
        sb.append("                <span class=\"contact-item\">&#128205; ").append(escapeHtml(data.getAdresse())).append("</span>\n");
        sb.append("                <span class=\"contact-item\">&#128049; ").append(escapeHtml(data.getGithub())).append("</span>\n");
        sb.append("            </div>\n");
        sb.append("        </div>\n");
        return sb.toString();
    }

    private String generatePersonalInfo(Data data, String dateNaissance) {
        StringBuilder sb = new StringBuilder();
        sb.append("        <div class=\"section\">\n");
        sb.append("            <h2 class=\"section-title\">INFORMATIONS PERSONNELLES</h2>\n");
        sb.append("            <table class=\"info-table\">\n");
        sb.append("                <tr>\n");
        sb.append("                    <td class=\"info-label\">Date de naissance</td>\n");
        sb.append("                    <td class=\"info-value\">").append(dateNaissance).append("</td>\n");
        sb.append("                    <td class=\"info-label\">Sexe</td>\n");
        sb.append("                    <td class=\"info-value\">").append(escapeHtml(data.getSexe())).append("</td>\n");
        sb.append("                </tr>\n");
        sb.append("            </table>\n");
        sb.append("        </div>\n");
        return sb.toString();
    }

    private String generateSkills(Data data) {
        StringBuilder sb = new StringBuilder();
        sb.append("        <div class=\"section\">\n");
        sb.append("            <h2 class=\"section-title\">COMP&#201;TENCES TECHNIQUES</h2>\n");
        sb.append("            <table class=\"skills-table\">\n");
        sb.append("                <tr>\n");

        // Colonne gauche
        sb.append("                    <td class=\"skills-col\">\n");
        sb.append("                        <div class=\"info-label\">Langues parl&#233;es</div>\n");
        sb.append("                        <div class=\"info-value\">").append(String.join(", ", data.getLangues())).append("</div>\n");
        sb.append("                        <br/>\n");
        sb.append("                        <div class=\"info-label\">Comp&#233;tences</div>\n");
        sb.append(generateSkillBadges(data.getCompetences()));
        sb.append("                    </td>\n");

        // Colonne droite
        sb.append("                    <td class=\"skills-col\">\n");
        sb.append("                        <div class=\"info-label\">Langages de programmation</div>\n");
        sb.append(generateSkillBadges(data.getLangages()));
        sb.append("                        <br/>\n");
        sb.append("                        <div class=\"info-label\">Bases de donn&#233;es</div>\n");
        sb.append(generateSkillBadges(data.getDatabases()));
        sb.append("                        <br/>\n");
        sb.append("                        <div class=\"info-label\">Outils &amp; Technologies</div>\n");
        sb.append(generateSkillBadges(data.getOutils()));
        sb.append("                    </td>\n");

        sb.append("                </tr>\n");
        sb.append("            </table>\n");
        sb.append("        </div>\n");
        return sb.toString();
    }

    // Remplace generateSkillItems — utilise des <span> au lieu de <ul>/<li>
    private String generateSkillBadges(List<String> skills) {
        if (skills == null || skills.isEmpty()) {
            return "                <div class=\"info-value\">Aucune s&#233;lection</div>\n";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("                        <div class=\"badges\">\n");
        for (String skill : skills) {
            sb.append("                            <span class=\"badge\">")
                    .append(escapeHtml(skill))
                    .append("</span>\n");
        }
        sb.append("                        </div>\n");
        return sb.toString();
    }

    private String generateExperience(Data data) {
        StringBuilder sb = new StringBuilder();
        sb.append("        <div class=\"section\">\n");
        sb.append("            <h2 class=\"section-title\">EXP&#201;RIENCE PROFESSIONNELLE</h2>\n");
        String[] experiences = data.getExperience().split("\n");
        for (String exp : experiences) {
            if (!exp.trim().isEmpty()) {
                sb.append("            <div class=\"item\">\n");
                sb.append("                <div class=\"item-description\">").append(escapeHtml(exp)).append("</div>\n");
                sb.append("            </div>\n");
            }
        }
        sb.append("        </div>\n");
        return sb.toString();
    }

    private String generateEducation(Data data) {
        StringBuilder sb = new StringBuilder();
        sb.append("        <div class=\"section\">\n");
        sb.append("            <h2 class=\"section-title\">FORMATION</h2>\n");
        String[] educations = data.getFormation().split("\n");
        for (String edu : educations) {
            if (!edu.trim().isEmpty()) {
                sb.append("            <div class=\"item\">\n");
                sb.append("                <div class=\"item-description\">").append(escapeHtml(edu)).append("</div>\n");
                sb.append("            </div>\n");
            }
        }
        sb.append("        </div>\n");
        return sb.toString();
    }

    private String generateProject(Data data) {
        StringBuilder sb = new StringBuilder();
        sb.append("        <div class=\"section\">\n");
        sb.append("            <h2 class=\"section-title\">PROJETS</h2>\n");
        String[] projects = data.getProjet().split("\n");
        for (String proj : projects) {
            if (!proj.trim().isEmpty()) {
                sb.append("            <div class=\"item\">\n");
                sb.append("                <div class=\"item-description\">").append(escapeHtml(proj)).append("</div>\n");
                sb.append("            </div>\n");
            }
        }
        sb.append("        </div>\n");
        return sb.toString();
    }

    private String getCSS() {
        StringBuilder css = new StringBuilder();

        // Reset général
        css.append("        * { margin: 0; padding: 0; box-sizing: border-box; }\n");
        css.append("        body {\n");
        css.append("            font-family: Arial, Helvetica, sans-serif;\n"); // ← polices PDF-safe
        css.append("            background-color: #f5f5f5;\n");
        css.append("            color: #333;\n");
        css.append("            line-height: 1.5;\n");
        css.append("        }\n");

        // Container principal
        css.append("        .cv-container {\n");
        css.append("            max-width: 900px;\n");
        css.append("            margin: 0 auto;\n");
        css.append("            background: white;\n");
        css.append("        }\n");

        // Header
        css.append("        .header {\n");
        css.append("            background-color: #2c3e50;\n");  // ← pas de gradient (mal supporté)
        css.append("            color: white;\n");
        css.append("            padding: 30px 40px;\n");
        css.append("            text-align: center;\n");
        css.append("        }\n");

        css.append("        .name {\n");
        css.append("            font-size: 26pt;\n");
        css.append("            font-weight: bold;\n");
        css.append("            margin-bottom: 10px;\n");
        css.append("        }\n");

        // Contact — PAS de display:flex
        css.append("        .contact-info {\n");
        css.append("            text-align: center;\n");
        css.append("            margin-top: 10px;\n");
        css.append("            font-size: 10pt;\n");
        css.append("        }\n");
        css.append("        .contact-item {\n");
        css.append("            display: inline-block;\n");
        css.append("            margin: 3px 12px;\n");
        css.append("            color: #ecf0f1;\n");
        css.append("        }\n");

        // Sections
        css.append("        .section {\n");
        css.append("            padding: 20px 40px;\n");
        css.append("            border-bottom: 1px solid #eee;\n");
        css.append("        }\n");
        css.append("        .section-title {\n");
        css.append("            color: #2c3e50;\n");
        css.append("            font-size: 13pt;\n");
        css.append("            font-weight: bold;\n");
        css.append("            margin-bottom: 15px;\n");
        css.append("            padding-bottom: 6px;\n");
        css.append("            border-bottom: 2px solid #3498db;\n");
        css.append("        }\n");

        // Table pour 2 colonnes — iText7 supporte bien les tableaux
        css.append("        .info-table {\n");
        css.append("            width: 100%;\n");
        css.append("            border-collapse: collapse;\n");
        css.append("        }\n");
        css.append("        .skills-table {\n");
        css.append("            width: 100%;\n");
        css.append("            border-collapse: collapse;\n");
        css.append("        }\n");
        css.append("        .skills-col {\n");
        css.append("            width: 50%;\n");
        css.append("            vertical-align: top;\n");
        css.append("            padding-right: 15px;\n");
        css.append("        }\n");

        // Labels et valeurs
        css.append("        .info-label {\n");
        css.append("            font-weight: bold;\n");
        css.append("            color: #555;\n");
        css.append("            font-size: 9pt;\n");
        css.append("            text-transform: uppercase;\n");
        css.append("            margin-bottom: 4px;\n");
        css.append("            padding: 6px 0;\n");
        css.append("        }\n");
        css.append("        .info-value {\n");
        css.append("            color: #333;\n");
        css.append("            margin-bottom: 8px;\n");
        css.append("        }\n");

        // Badges (remplace les <li> flex)
        css.append("        .badges {\n");
        css.append("            margin: 4px 0 8px 0;\n");
        css.append("        }\n");
        css.append("        .badge {\n");
        css.append("            display: inline-block;\n");
        css.append("            background-color: #e8f4fc;\n");
        css.append("            color: #2c3e50;\n");
        css.append("            border: 1px solid #3498db;\n");
        css.append("            padding: 3px 10px;\n");
        css.append("            margin: 3px 3px 3px 0;\n");
        css.append("            font-size: 9pt;\n");
        css.append("        }\n");

        // Items expérience / formation / projets
        css.append("        .item {\n");
        css.append("            margin-bottom: 10px;\n");
        css.append("            padding-left: 10px;\n");
        css.append("            border-left: 3px solid #3498db;\n");
        css.append("        }\n");
        css.append("        .item-description {\n");
        css.append("            color: #444;\n");
        css.append("            font-size: 10pt;\n");
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