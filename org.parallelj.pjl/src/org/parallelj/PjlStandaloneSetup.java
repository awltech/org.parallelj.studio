
package org.parallelj;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class PjlStandaloneSetup extends PjlStandaloneSetupGenerated{

	public static void doSetup() {
		new PjlStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

