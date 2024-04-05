function getName(playerName) {
	return playerName.substring(2);
}
function getIcon(playerName) {
	return playerName.substring(0,2);
}

function stringToInt(string) {
	try {
		return Number.parseInt(string);
	} catch (e) {
		errorHandler(e);
		return null;
	}
}

function nameToParameter(string){
	return string.replace(/\s/g, '_');
}
