package IHM.CV.Services;
import IHM.CV.Services.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {

        private static final String EMAIL_PATTERN =
                "^[A-Za-z0-9+_.-]+@(.+)$";
        private static final String PHONE_PATTERN =
                "^[0-9+\\s-]{8,}$";

        public ValidationResult validate(Data data) {
            ValidationResult result = new ValidationResult();

            // Validation des champs obligatoires
            if (data.getNom() == null || data.getNom().trim().isEmpty()) {
                result.addError("Le nom est obligatoire");
            }

            if (data.getPrenom() == null || data.getPrenom().trim().isEmpty()) {
                result.addError("Le prénom est obligatoire");
            }

            if (data.getEmail() == null || data.getEmail().trim().isEmpty()) {
                result.addError("L'email est obligatoire");
            }

            if (data.getExperience() == null || data.getExperience().trim().isEmpty()) {
                result.addError("Les expériences sont obligatoires");
            }

            if (data.getFormation() == null || data.getFormation().trim().isEmpty()) {
                result.addError("La formation est obligatoire");
            }

            if (data.getProjet() == null || data.getProjet().trim().isEmpty()) {
                result.addError("Les projets sont obligatoires");
            }

            if (data.getEmail() != null && !data.getEmail().isEmpty()) {
                if (!Pattern.matches(EMAIL_PATTERN, data.getEmail())) {
                    result.addError("Format d'email invalide");
                }
            }

            if (data.getTelephone() != null && !data.getTelephone().isEmpty()) {
                if (!Pattern.matches(PHONE_PATTERN, data.getTelephone())) {
                    result.addWarning("Format de téléphone suspect");
                }
            }

            return result;
        }

        public static class ValidationResult {
            private List<String> errors = new ArrayList<>();
            private List<String> warnings = new ArrayList<>();

            public void addError(String error) { errors.add(error); }
            public void addWarning(String warning) { warnings.add(warning); }

            public boolean isValid() { return errors.isEmpty(); }
            public List<String> getErrors() { return errors; }
            public List<String> getWarnings() { return warnings; }

            public String getErrorMessage() {
                return String.join("\n", errors);
            }
        }
    }

