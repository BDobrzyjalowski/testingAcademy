function createEntityData(Entity, EntityProcessorData){
	var directoryProcessor = Java.type('com.bmw.psmg.sbb.interfaces.rest.directory.DirectoryProcessor');
	return directoryProcessor.createFolderChain(Entity,EntityProcessorData);
}

