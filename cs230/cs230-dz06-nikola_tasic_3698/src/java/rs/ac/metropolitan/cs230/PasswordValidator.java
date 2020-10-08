package rs.ac.metropolitan.cs230;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("passwordsValidator")
public class PasswordValidator implements Validator<String> {

	@Override
	public void validate(FacesContext context, UIComponent component, String confirm) throws ValidatorException {
		String password = (String) component.findComponent("password").getAttributes().get("value");

		if (!password.equals(confirm)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Passwords do not match", null));
		}
	}
}
