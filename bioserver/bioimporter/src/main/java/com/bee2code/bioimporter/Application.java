package com.bee2code.bioimporter;

import com.bee2code.bioimporter.exception.ImporterServiceException;
import com.bee2code.bioimporter.exception.InputArgumentsException;
import com.bee2code.bioimporter.service.IImporterService;
import com.bee2code.bioimporter.service.ImporterService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

	public static void main(String[] args) throws InputArgumentsException {
		IImporterService importerService = new ImporterService();
		try {
			if (args.length == 0 || args[0].isEmpty()) {
				throw new InputArgumentsException("No json configuration file path specified");
			}
			importerService.run(args[0]);
		} catch (ImporterServiceException e) {
			log.error("Import failed", e);
		}
	}

}
