/**
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
package nl.esciencecenter.xenon.adaptors.local;

import nl.esciencecenter.xenon.adaptors.CredentialTestConfig;

/**
 * @version 1.0
 * @since 1.0
 *
 */
public class LocalCredentialTestConfig implements CredentialTestConfig {

    @Override
    public boolean supportsCertificateCredentials() {
        return false;
    }

    @Override
    public boolean supportsPasswordCredentials() {
        return false;
    }

    @Override
    public String getCorrectCertFile() {
        return null;
    }

    @Override
    public String getIncorrectCertFile() {
        return null;
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public char[] getPassword() {
        return null;
    }

    @Override
    public String[] supportedSchemes() {
        return new String [] { "local", "file" };
    }
}
