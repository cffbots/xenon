/*
 * Copyright 2013 Netherlands eScience Center
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.esciencecenter.cobalt.engine.credentials;

import nl.esciencecenter.cobalt.engine.CobaltProperties;

/**
 * A {@link Credential} based on a user name and password.
 */
public class PasswordCredentialImplementation extends CredentialImplementation {

    /**
     * Constructs a {@link PasswordCredentialImplementation} with the given <code>username</code> and <code>password</code>.
     * 
     * @param username
     *            the user name
     * @param password
     *            the password for the given user name
     */
    public PasswordCredentialImplementation(String adaptorName, String uniqueID, CobaltProperties properties, String username,
            char[] password) {
        super(adaptorName, uniqueID, properties, username, password);
    }

    @Override
    public String toString() {
        return "PasswordCredentialImplementation [adaptorName=" + getAdaptorName() + ", username=" + getUsername() + "]";
    }
}