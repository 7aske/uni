import { environment } from "../environments/environment";

const Console = {
	log: (() => environment.production ? () => void 0 : console.log)(),
	error: (() => environment.production ? () => void 0 : console.error)(),
	warn: (() => environment.production ? () => void 0 : console.warn)(),
	info: (() => environment.production ? () => void 0 : console.info)(),
};

export default Console;
