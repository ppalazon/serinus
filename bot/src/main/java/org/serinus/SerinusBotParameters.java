/*
 * Copyright 2011. Pablo Palazon (pablo.palazon@gmail.com)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.serinus;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.logging.Category;
import org.jboss.weld.logging.LoggerFactory;
import org.serinus.config.SerinusBotConfig;
import org.slf4j.cal10n.LocLogger;

@ApplicationScoped
public class SerinusBotParameters {

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	private LocLogger log = LoggerFactory.loggerFactory().getLogger(
			Category.BOOTSTRAP);

	@Inject
	@Parameters
	List<String> params;
	private List<String> errors = new ArrayList<String>();

	@Inject
	SerinusBotConfig serinusBotConfig;

	@Inject
	public void validateParameters() {
		CommandLineParser parser = new GnuParser();
		try {
			CommandLine line = parser.parse(getCLIOptions(),
					params.toArray(new String[] {}));
			if (line.hasOption("host")) {
				serinusBotConfig.setHost(line.getOptionValue("host"));
			}
			if (line.hasOption("port")) {
				serinusBotConfig.setPort(Integer.valueOf(line
						.getOptionValue("port")));
			}
			if (line.hasOption("user")) {
				serinusBotConfig.setUserBot(line.getOptionValue("user"));
			}
			if (line.hasOption("password")) {
				serinusBotConfig
						.setPasswordBot(line.getOptionValue("password"));
			}
		} catch (ParseException e) {
			errors.add(e.getMessage());
		}
	}

	@SuppressWarnings("static-access")
	public Options getCLIOptions() {
		Option host = OptionBuilder.withArgName("host").hasArg()
				.withDescription("xmpp server host").isRequired(true)
				.create("host");

		Option user = OptionBuilder.withArgName("user").hasArg()
				.withDescription("xmpp user for connecting to host")
				.isRequired(true).create("user");

		Option pass = OptionBuilder.withArgName("password").hasArg()
				.withDescription("password for user").isRequired(true)
				.create("password");

		Option port = OptionBuilder.withArgName("port").hasArg()
				.withDescription("xmpp port server").create("port");

		Options options = new Options();

		options.addOption(host);
		options.addOption(port);
		options.addOption(user);
		options.addOption(pass);

		return options;
	}

}
