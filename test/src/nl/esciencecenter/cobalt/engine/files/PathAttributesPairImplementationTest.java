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

package nl.esciencecenter.cobalt.engine.files;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import nl.esciencecenter.cobalt.Cobalt;
import nl.esciencecenter.cobalt.CobaltException;
import nl.esciencecenter.cobalt.CobaltFactory;
import nl.esciencecenter.cobalt.engine.files.PathAttributesPairImplementation;
import nl.esciencecenter.cobalt.files.FileAttributes;
import nl.esciencecenter.cobalt.files.FileSystem;
import nl.esciencecenter.cobalt.files.Files;
import nl.esciencecenter.cobalt.files.Path;
import nl.esciencecenter.cobalt.util.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jason Maassen <J.Maassen@esciencecenter.nl>
 * 
 */
public class PathAttributesPairImplementationTest {

    Cobalt octopus;
    Files files;
    FileSystem filesystem;
    Path root;
    FileAttributes att;

    @Before
    public void prepare() throws CobaltException, CobaltException {

        octopus = CobaltFactory.newCobalt(null);
        files = octopus.files();
        root = Utils.getLocalCWD(files);
        filesystem = root.getFileSystem();
        att = files.getAttributes(root);
    }

    @After
    public void cleanup() throws CobaltException, CobaltException {
        files.close(filesystem);
        CobaltFactory.endCobalt(octopus);
    }

    @Test
    public void test_equals() throws CobaltException {

        PathAttributesPairImplementation tmp = new PathAttributesPairImplementation(root, att);
        PathAttributesPairImplementation tmp2 = new PathAttributesPairImplementation(root, null);
        PathAttributesPairImplementation tmp3 = new PathAttributesPairImplementation(null, att);

        PathAttributesPairImplementation tmp4 = new PathAttributesPairImplementation(root, att);

        PathAttributesPairImplementation tmp5 = new PathAttributesPairImplementation(root, null);
        PathAttributesPairImplementation tmp6 = new PathAttributesPairImplementation(null, att);

        assertFalse(tmp.equals(null));
        assertFalse(tmp.equals("AAP"));
        assertTrue(tmp.equals(tmp));
        assertFalse(tmp.equals(tmp2));
        assertFalse(tmp2.equals(tmp));
        assertTrue(tmp2.equals(tmp5));
        assertFalse(tmp.equals(tmp3));
        assertFalse(tmp3.equals(tmp));
        assertTrue(tmp3.equals(tmp6));
        assertTrue(tmp.equals(tmp4));
    }

    @Test
    public void test_hashCode() throws CobaltException {

        final int prime = 31;
        int result = 1;
        result = prime * result + att.hashCode();
        result = prime * result + root.hashCode();

        assertTrue(result == new PathAttributesPairImplementation(root, att).hashCode());

        result = 1;
        result = prime * result + 0;
        result = prime * result + root.hashCode();

        assertTrue(result == new PathAttributesPairImplementation(root, null).hashCode());

        result = 1;
        result = prime * result + att.hashCode();
        result = prime * result + 0;

        assertTrue(result == new PathAttributesPairImplementation(null, att).hashCode());

        result = 1;
        result = prime * result + 0;
        result = prime * result + 0;

        assertTrue(result == new PathAttributesPairImplementation(null, null).hashCode());

        PathAttributesPairImplementation tmp1 = new PathAttributesPairImplementation(root, att);
        PathAttributesPairImplementation tmp2 = new PathAttributesPairImplementation(root, att);

        assertTrue(tmp1.equals(tmp2));
        assertTrue(tmp1.hashCode() == tmp2.hashCode());
    }

}