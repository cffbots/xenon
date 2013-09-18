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
package nl.esciencecenter.cobalt.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import nl.esciencecenter.cobalt.InvalidPropertyException;
import nl.esciencecenter.cobalt.CobaltPropertyDescription;
import nl.esciencecenter.cobalt.UnknownPropertyException;
import nl.esciencecenter.cobalt.CobaltPropertyDescription.Component;
import nl.esciencecenter.cobalt.CobaltPropertyDescription.Type;
import nl.esciencecenter.cobalt.engine.CobaltProperties;
import nl.esciencecenter.cobalt.engine.CobaltPropertyDescriptionImplementation;
import nl.esciencecenter.cobalt.engine.PropertyTypeException;
import nl.esciencecenter.cobalt.engine.util.ImmutableArray;

import org.junit.Test;

public class CobaltPropertiesTest {

    @Test
    public void testOctopusProperties_supportsProperty_propertySet_true() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.STRING, EnumSet.of(Component.COBALT), "bla", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "value");

        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertTrue(octprop.supportsProperty("key"));
    }

    @Test
    public void testOctopusProperties_supportsProperty_useDefault_true() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.STRING, EnumSet.of(Component.COBALT), "bla", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertTrue(octprop.supportsProperty("key"));
    }

    @Test
    public void testOctopusProperties_supportsProperty_propertySet_false() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.STRING, EnumSet.of(Component.COBALT), "bla", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "value");

        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertFalse(octprop.supportsProperty("aap"));
    }

    @Test
    public void testOctopusProperties_supportsProperty_useDefault_false() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.STRING, EnumSet.of(Component.COBALT), "bla", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertFalse(octprop.supportsProperty("aap"));
    }
    
    @Test
    public void testOctopusProperties_propertySet_true() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.STRING, EnumSet.of(Component.COBALT), "bla", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "value");

        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertTrue(octprop.propertySet("key"));
    }

    @Test
    public void testOctopusProperties_propertySet_false() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.STRING, EnumSet.of(Component.COBALT), "bla", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertFalse(octprop.propertySet("key"));
    }
    
    @Test(expected = UnknownPropertyException.class)
    public void testOctopusProperties_propertySet_fails() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.STRING, EnumSet.of(Component.COBALT), "bla", 
                        "test property"));

        Map<String, String> props = new HashMap<>();        
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertFalse(octprop.propertySet("aap"));
    }
 

    @Test
    public void testOctopusProperties_getProperty_propertySet() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.STRING, EnumSet.of(Component.COBALT), "bla", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "value");

        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertEquals("value", octprop.getProperty("key"));
    }

    @Test
    public void testOctopusProperties_getProperty_default() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.STRING, EnumSet.of(Component.COBALT), "bla", 
                        "test property"));

        Map<String, String> props = new HashMap<>();

        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertEquals("bla", octprop.getProperty("key"));
    }

    @Test(expected = UnknownPropertyException.class)
    public void testOctopusProperties_getProperty_fails() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.STRING, EnumSet.of(Component.COBALT), "bla", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "value");

        CobaltProperties octprop = new CobaltProperties(valid, props);
        octprop.getProperty("aap"); // throws exception
    }
    
    @Test
    public void testOctopusProperties_fromProperties() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.STRING, EnumSet.of(Component.COBALT), "bla", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "value");

        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertEquals("{key=value}", octprop.toString());
    }

    @Test(expected = UnknownPropertyException.class)
    public void testOctopusProperties_fromDefaultsAndProperties_noOverlap() throws Exception {
        
        ImmutableArray<CobaltPropertyDescription> supportedProperties = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.STRING, EnumSet.of(Component.COBALT), "value", 
                        "test property"));
        
        Map<String, String> props = new HashMap<>();
        props.put("key2", "value2");

        new CobaltProperties(supportedProperties, props);
    }

    @Test
    public void testOctopusProperties_fromDefaultsAndProperties_withOverlap() throws Exception {
        
        ImmutableArray<CobaltPropertyDescription> supportedProperties = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.STRING, EnumSet.of(Component.COBALT), "value",
                        "test property"),
                new CobaltPropertyDescriptionImplementation("key2", Type.STRING, EnumSet.of(Component.COBALT), "value",
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key2", "value2");

        CobaltProperties octprop = new CobaltProperties(supportedProperties, props);

        assertEquals("{key2=value2, <<key=value>>}", octprop.toString());
    }

    @Test
    public void testOctopusProperties_fromDefaultsAndProperties_Filter() throws Exception {
        
        ImmutableArray<CobaltPropertyDescription> supportedProperties = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.STRING, EnumSet.of(Component.COBALT), "value",
                        "test property"),
                new CobaltPropertyDescriptionImplementation("key2", Type.STRING, EnumSet.of(Component.SCHEDULER), "value",
                        "test property"));
                
        Map<String, String> props = new HashMap<>();
        props.put("key2", "value2");

        CobaltProperties octprop = new CobaltProperties(supportedProperties, props).filter(Component.COBALT);
        assertEquals("{<<key=value>>}", octprop.toString());
    }

    @Test
    public void testGetBooleanProperty_true() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.BOOLEAN, EnumSet.of(Component.COBALT), "true", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "true");
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertTrue(octprop.getBooleanProperty("key"));
    }

    @Test
    public void testGetBooleanProperty_false() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.BOOLEAN, EnumSet.of(Component.COBALT), "true", 
                        "test property"));
        
        Map<String, String> props = new HashMap<>();
        props.put("key", "false");
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertFalse(octprop.getBooleanProperty("key"));
    }

    @Test
    public void testGetBooleanProperty_default() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.BOOLEAN, EnumSet.of(Component.COBALT), "true", 
                        "test property"));
        
        Map<String, String> props = new HashMap<>();
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertTrue(octprop.getBooleanProperty("key"));
    }

    @Test(expected = InvalidPropertyException.class)
    public void testGetBooleanProperty_emptyString_False() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.BOOLEAN, EnumSet.of(Component.COBALT), "true", 
                        "test property"));
        
        Map<String, String> props = new HashMap<>();
        props.put("key", "bla");
        new CobaltProperties(valid, props);
    }

    @Test(expected = InvalidPropertyException.class)
    public void testGetBooleanProperty_invalidDefault() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.BOOLEAN, EnumSet.of(Component.COBALT), "aap", 
                        "test property"));
        
        Map<String, String> props = new HashMap<>();
        new CobaltProperties(valid, props).getBooleanProperty("key");
    }

    @Test(expected = UnknownPropertyException.class)
    public void testGetBooleanProperty_invalidName() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.BOOLEAN, EnumSet.of(Component.COBALT), "true", 
                        "test property"));
        
        Map<String, String> props = new HashMap<>();
        new CobaltProperties(valid, props).getBooleanProperty("noot");
    }
    
    @Test(expected = PropertyTypeException.class)
    public void testGetBooleanProperty_wrongType() throws Exception {

        ImmutableArray<CobaltPropertyDescription> supportedProperties = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.STRING, EnumSet.of(Component.COBALT), "value", 
                        "test property"));
        
        Map<String, String> props = new HashMap<>();
        CobaltProperties octprop = new CobaltProperties(supportedProperties, props);
        octprop.getBooleanProperty("key"); // throws exception        
    }
    
    @Test
    public void testGetIntProperty_1() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.INTEGER, EnumSet.of(Component.COBALT), "42", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "1");
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertTrue(octprop.getIntegerProperty("key") == 1);
    }

    @Test
    public void testGetIntProperty_default() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.INTEGER, EnumSet.of(Component.COBALT), "42", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertTrue(octprop.getIntegerProperty("key") == 42);
    }

    @Test(expected = InvalidPropertyException.class)
    public void testGetIntProperty_invalidDefault() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.INTEGER, EnumSet.of(Component.COBALT), "aap", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        new CobaltProperties(valid, props).getIntegerProperty("key"); // throws exception
    }

    @Test
    public void testGetDoubleProperty_1() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.DOUBLE, EnumSet.of(Component.COBALT), "42.0", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "1.0");
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertTrue(octprop.getDoubleProperty("key") == 1.0);
    }

    @Test
    public void testGetDoubleProperty_default() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.DOUBLE, EnumSet.of(Component.COBALT), "42.0", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertTrue(octprop.getDoubleProperty("key") == 42.0);
    }

    @Test(expected = InvalidPropertyException.class)
    public void testGetDoubleProperty_invalidDefault() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.DOUBLE, EnumSet.of(Component.COBALT), "aap", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        new CobaltProperties(valid, props).getDoubleProperty("key"); // throws exception
    }

    
    @Test
    public void testGetSizeProperty_g() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.SIZE, EnumSet.of(Component.COBALT), "42g", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "1g");
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertTrue(octprop.getSizeProperty("key") == 1L*1024L*1024L*1024L);
    }
    
    @Test
    public void testGetSizeProperty_G() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.SIZE, EnumSet.of(Component.COBALT), "42g", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "1G");
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertTrue(octprop.getSizeProperty("key") == 1L*1024L*1024L*1024L);
    }

    @Test
    public void testGetSizeProperty_m() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.SIZE, EnumSet.of(Component.COBALT), "42g", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "1m");
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertTrue(octprop.getSizeProperty("key") == 1L*1024L*1024L);
    }
    
    @Test
    public void testGetSizeProperty_M() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.SIZE, EnumSet.of(Component.COBALT), "42g", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "1M");
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertTrue(octprop.getSizeProperty("key") == 1L*1024L*1024L);
    }

    @Test
    public void testGetSizeProperty_k() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.SIZE, EnumSet.of(Component.COBALT), "42g", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "1k");
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertTrue(octprop.getSizeProperty("key") == 1L*1024L);
    }
    
    @Test
    public void testGetSizeProperty_K() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.SIZE, EnumSet.of(Component.COBALT), "42g", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "1K");
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertTrue(octprop.getSizeProperty("key") == 1L*1024L);
    }

    @Test
    public void testGetSizeProperty() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.SIZE, EnumSet.of(Component.COBALT), "42g", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "1");
        CobaltProperties octprop = new CobaltProperties(valid, props);

        assertTrue(octprop.getSizeProperty("key") == 1L);
    }
    
    @Test(expected = InvalidPropertyException.class)
    public void testGetSizeProperty_X_fails() throws Exception {

        ImmutableArray<CobaltPropertyDescription> valid = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("key", Type.SIZE, EnumSet.of(Component.COBALT), "42g", 
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("key", "1X");
        CobaltProperties octprop = new CobaltProperties(valid, props);
        octprop.getSizeProperty("key"); // throws exception
    }

    @Test
    public void testOctopusProperties_filter_withPropertiesSet() throws Exception {
        
        ImmutableArray<CobaltPropertyDescription> supportedProperties = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("aap.key", Type.STRING, EnumSet.of(Component.COBALT), "aap",
                        "test property"),
                new CobaltPropertyDescriptionImplementation("noot.key", Type.STRING, EnumSet.of(Component.COBALT), "noot",
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("aap.key", "aap2");
        props.put("noot.key", "noot2");
        
        CobaltProperties octprop = new CobaltProperties(supportedProperties, props).filter("aap");
        
        assertEquals("{aap.key=aap2}", octprop.toString());
    }
    
    @Test
    public void testOctopusProperties_filter_noPropertiesSet() throws Exception {
        
        ImmutableArray<CobaltPropertyDescription> supportedProperties = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("aap.key", Type.STRING, EnumSet.of(Component.COBALT), "aap",
                        "test property"),
                new CobaltPropertyDescriptionImplementation("noot.key", Type.STRING, EnumSet.of(Component.COBALT), "noot",
                        "test property"));

        Map<String, String> props = new HashMap<>();
        
        CobaltProperties octprop = new CobaltProperties(supportedProperties, props).filter("aap");
        
        assertEquals("{<<aap.key=aap>>}", octprop.toString());
    }

    @Test
    public void testOctopusProperties_filter_wrongPrefix() throws Exception {
        
        ImmutableArray<CobaltPropertyDescription> supportedProperties = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("aap.key", Type.STRING, EnumSet.of(Component.COBALT), "aap",
                        "test property"),
                new CobaltPropertyDescriptionImplementation("noot.key", Type.STRING, EnumSet.of(Component.COBALT), "noot",
                        "test property"));

        Map<String, String> props = new HashMap<>();
        
        CobaltProperties octprop = new CobaltProperties(supportedProperties, props).filter("bla");
        
        assertEquals("{}", octprop.toString());
    }

    @Test
    public void testOctopusProperties_exclude_withPropertiesSet() throws Exception {
        
        ImmutableArray<CobaltPropertyDescription> supportedProperties = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("aap.key", Type.STRING, EnumSet.of(Component.COBALT), "aap",
                        "test property"),
                new CobaltPropertyDescriptionImplementation("noot.key", Type.STRING, EnumSet.of(Component.COBALT), "noot",
                        "test property"));

        Map<String, String> props = new HashMap<>();
        props.put("aap.key", "aap2");
        props.put("noot.key", "noot2");
        
        CobaltProperties octprop = new CobaltProperties(supportedProperties, props).exclude("noot");
        
        assertEquals("{aap.key=aap2}", octprop.toString());
    }
    
    @Test
    public void testOctopusProperties_exclude_noPropertiesSet() throws Exception {
        
        ImmutableArray<CobaltPropertyDescription> supportedProperties = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("aap.key", Type.STRING, EnumSet.of(Component.COBALT), "aap",
                        "test property"),
                new CobaltPropertyDescriptionImplementation("noot.key", Type.STRING, EnumSet.of(Component.COBALT), "noot",
                        "test property"));

        Map<String, String> props = new HashMap<>();
        
        CobaltProperties octprop = new CobaltProperties(supportedProperties, props).exclude("noot");
        
        assertEquals("{<<aap.key=aap>>}", octprop.toString());
    }

    @Test
    public void testOctopusProperties_exclude_wrongPrefix() throws Exception {
        
        ImmutableArray<CobaltPropertyDescription> supportedProperties = new ImmutableArray<CobaltPropertyDescription>(
                new CobaltPropertyDescriptionImplementation("aap.key", Type.STRING, EnumSet.of(Component.COBALT), "aap",
                        "test property"),
                new CobaltPropertyDescriptionImplementation("noot.key", Type.STRING, EnumSet.of(Component.COBALT), "noot",
                        "test property"));

        Map<String, String> props = new HashMap<>();
        
        CobaltProperties octprop = new CobaltProperties(supportedProperties, props).exclude("bla");
        
        assertEquals("{<<noot.key=noot>>, <<aap.key=aap>>}", octprop.toString());
    }
    
    
    
    
    
    
    
    
    /*
     * FIXME!!!
     * 
        @Test(expected = InvalidPropertyException.class) 
        public void testGetIntegerProperty_emptyString_False() throws Exception {

            OctopusPropertyDescription [] valid = new OctopusPropertyDescription [] { 
                 new OctopusPropertyDescriptionImplementation("key", Type.INTEGER, EnumSet.of(Level.OCTOPUS), "42", "test property")
            };

            Map<String,String> props = new HashMap<>();
            props.put("key", "bla");
            new OctopusProperties(valid, props);
        }

        @Test(expected = PropertyTypeException.class) 
        public void testGetIntegerProperty_emptyString_False() throws Exception {

            OctopusPropertyDescription [] valid = new OctopusPropertyDescription [] { 
                 new OctopusPropertyDescriptionImplementation("key", Type.INTEGER, EnumSet.of(Level.OCTOPUS), "42", "test property")
            };

            Map<String,String> props = new HashMap<>();
            props.put("key", "bla");
            new OctopusProperties(valid, props);
        }

        
        
        
        
        
        
        
        @Test
        public void testGetIntProperty_int_int() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "42");
            OctopusProperties octprop = new OctopusProperties(props);

            int result = octprop.getIntProperty("key");

            assertTrue(result == 42);
        }

        @Test
        public void testGetIntProperty_null_UndefinedException() throws Exception {
            Map<String,String> props = new HashMap<>();
            OctopusProperties octprop = new OctopusProperties(props);

            try {
                octprop.getIntProperty("key");
                fail("No NumberFormatException thrown");
            } catch (NumberFormatException e) {
                assertTrue(e.getMessage().equals("property undefined: key"));
            }
        }

        @Test(expected = NumberFormatException.class)
        public void testGetIntProperty_null_IntParseException() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "foo");
            OctopusProperties octprop = new OctopusProperties(props);
            octprop.getIntProperty("key");
        }

        @Test(expected = NumberFormatException.class)
        public void testGetIntProperty_null_IntParseException2() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "foo");
            OctopusProperties octprop = new OctopusProperties(props);
            octprop.getIntProperty("key", 42);
        }

        @Test
        public void testGetIntProperty_withDefault2() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "33");
            OctopusProperties octprop = new OctopusProperties(props);

            int result = octprop.getIntProperty("key", 42);

            assertTrue(result == 33);
        }

        @Test
        public void testGetIntProperty_withDefault() throws Exception {
            OctopusProperties octprop = new OctopusProperties();

            int result = octprop.getIntProperty("key", 42);

            assertTrue(result == 42);
        }

        @Test
        public void testGetIntProperty_SetAndDefault_returnsSet() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "13");
            OctopusProperties octprop = new OctopusProperties(props);

            int result = octprop.getIntProperty("key", 42);

            assertTrue(result == 13);
        }

        @Test
        public void testGetLongProperty_long_long() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "42");
            OctopusProperties octprop = new OctopusProperties(props);

            long result = octprop.getLongProperty("key");

            assertTrue(result == 42);
        }

        @Test
        public void testGetLongProperty_null_UndefinedException() throws Exception {
            Map<String,String> props = new HashMap<>();
            OctopusProperties octprop = new OctopusProperties(props);

            try {
                octprop.getLongProperty("key");
                fail("No NumberFormatException thrown");
            } catch (NumberFormatException e) {
                assertTrue(e.getMessage().equals("property undefined: key"));
            }
        }

        @Test(expected = NumberFormatException.class)
        public void testGetLongProperty_null_LongParseException() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "foo");
            OctopusProperties octprop = new OctopusProperties(props);
            octprop.getLongProperty("key");
        }

        @Test(expected = NumberFormatException.class)
        public void testGetLongProperty_null_LongParseException2() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "foo");
            OctopusProperties octprop = new OctopusProperties(props);
            octprop.getLongProperty("key", 42L);
        }

        @Test
        public void testGetLongProperty_withDefault() throws Exception {
            OctopusProperties octprop = new OctopusProperties();

            long result = octprop.getLongProperty("key", 42);

            assertTrue(result == 42);
        }

        @Test
        public void testGetLongProperty_SetAndDefault_returnsSet() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "13");
            OctopusProperties octprop = new OctopusProperties(props);

            long result = octprop.getLongProperty("key", 42);

            assertTrue(result == 13);
        }

        @Test
        public void testGetShortProperty_short_short() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "42");
            OctopusProperties octprop = new OctopusProperties(props);

            short result = octprop.getShortProperty("key");

            short expected = 42;
            assertTrue(result == expected);
        }

        @Test
        public void testGetShortProperty_null_UndefinedException() throws Exception {
            Map<String,String> props = new HashMap<>();
            OctopusProperties octprop = new OctopusProperties(props);

            try {
                octprop.getShortProperty("key");
                fail("No NumberFormatException thrown");
            } catch (NumberFormatException e) {
                assertTrue(e.getMessage().equals("property undefined: key"));
            }
        }

        @Test(expected = NumberFormatException.class)
        public void testGetShortProperty_null_ShortParseException() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "foo");
            OctopusProperties octprop = new OctopusProperties(props);
            octprop.getShortProperty("key");
        }

        @Test(expected = NumberFormatException.class)
        public void testGetShortProperty_null_ShortParseException2() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "foo");
            OctopusProperties octprop = new OctopusProperties(props);
            octprop.getShortProperty("key", (short) 42);
        }

        @Test
        public void testGetShortProperty_withDefault() throws Exception {
            OctopusProperties octprop = new OctopusProperties();
            short default_value = 42;

            short result = octprop.getShortProperty("key", default_value);

            assertTrue(result == 42);
        }

        @Test
        public void testGetShortProperty_SetAndDefault_returnsSet() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "13");
            OctopusProperties octprop = new OctopusProperties(props);

            short default_value = 42;
            short result = octprop.getShortProperty("key", default_value);

            assertTrue(result == 13);
        }

        @Test
        public void testGetDoubleProperty_double_double() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "42.123");
            OctopusProperties octprop = new OctopusProperties(props);

            double result = octprop.getDoubleProperty("key");

            assertTrue(result == 42.123);
        }

        @Test
        public void testGetDoubleProperty_null_UndefinedException() throws Exception {
            Map<String,String> props = new HashMap<>();
            OctopusProperties octprop = new OctopusProperties(props);

            try {
                octprop.getDoubleProperty("key");
                fail("No NumberFormatException thrown");
            } catch (NumberFormatException e) {
                assertTrue(e.getMessage().equals("property undefined: key"));
            }
        }

        @Test(expected = NumberFormatException.class)
        public void testGetDoubleProperty_null_DoubleParseException() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "foo");
            OctopusProperties octprop = new OctopusProperties(props);
            octprop.getDoubleProperty("key");
        }

        @Test
        public void testGetDoubleProperty_withDefault() throws Exception {
            OctopusProperties octprop = new OctopusProperties();
            double default_value = 42.123;

            double result = octprop.getDoubleProperty("key", default_value);

            assertTrue(result == 42.123);
        }

        @Test(expected = NumberFormatException.class)
        public void testGetDoubleProperty_withDefault_DoubleParseException() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "foo");
            OctopusProperties octprop = new OctopusProperties(props);
            octprop.getDoubleProperty("key", 42.0);
        }

        @Test
        public void testGetDoubleProperty_SetAndDefault_returnsSet() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "13.456");
            OctopusProperties octprop = new OctopusProperties(props);

            double default_value = 42.123;
            double result = octprop.getDoubleProperty("key", default_value);

            assertTrue(result == 13.456);
        }

        @Test
        public void testGetFloatProperty_float_float() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "42.123f");
            OctopusProperties octprop = new OctopusProperties(props);

            float result = octprop.getFloatProperty("key");

            assertTrue(result == 42.123f);
        }

        @Test(expected = NumberFormatException.class)
        public void testGetFloatProperty_null_UndefinedException() throws Exception {
            Map<String,String> props = new HashMap<>();
            new OctopusProperties(props).getFloatProperty("key");
        }

        @Test(expected = NumberFormatException.class)
        public void testGetFloatProperty_null_FloatParseException() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "foo");
            new OctopusProperties(props).getFloatProperty("key");

        }

        @Test
        public void testGetFloatProperty_withDefault() throws Exception {
            OctopusProperties octprop = new OctopusProperties();
            float default_value = 42.123f;

            float result = octprop.getFloatProperty("key", default_value);

            assertTrue(result == 42.123f);
        }

        @Test(expected = NumberFormatException.class)
        public void testGetFloatProperty_withDefault_FloatParseException() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "foo");
            new OctopusProperties(props).getFloatProperty("key", 42.0f);
        }

        @Test
        public void testGetFloatProperty_SetAndDefault_returnsSet() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "13.456f");
            OctopusProperties octprop = new OctopusProperties(props);

            float default_value = 42.123f;
            float result = octprop.getFloatProperty("key", default_value);

            assertTrue(result == 13.456f);
        }

        @Test
        public void testGetSizeProperty() throws Exception {

            Map<String,String> props = new HashMap<>();
            props.put("B", "100");
            props.put("K", "100K");
            props.put("M", "100M");
            props.put("G", "100G");

            OctopusProperties octprop = new OctopusProperties(props);

            long result = octprop.getSizeProperty("B");

            assertTrue(result == 100L);

            result = octprop.getSizeProperty("K");

            assertTrue(result == (100L * 1024L));

            result = octprop.getSizeProperty("M");

            assertTrue(result == (100L * 1024L * 1024L));

            result = octprop.getSizeProperty("G");

            assertTrue(result == (100L * 1024L * 1024L * 1024L));
        }

        @Test
        public void testGetSizeProperty1() throws Exception {

            Map<String,String> props = new HashMap<>();
            props.put("B", "100");
            props.put("K", "100k");
            props.put("M", "100m");
            props.put("G", "100g");

            OctopusProperties octprop = new OctopusProperties(props);

            long result = octprop.getSizeProperty("B");

            assertTrue(result == 100L);

            result = octprop.getSizeProperty("K");

            assertTrue(result == (100L * 1024L));

            result = octprop.getSizeProperty("M");

            assertTrue(result == (100L * 1024L * 1024L));

            result = octprop.getSizeProperty("G");

            assertTrue(result == (100L * 1024L * 1024L * 1024L));
        }

        @Test(expected = NumberFormatException.class)
        public void testGetSizeProperty2() throws Exception {

            Map<String,String> props = new HashMap<>();
            props.put("K", "100Q");

            OctopusProperties octprop = new OctopusProperties(props);

            octprop.getSizeProperty("K");
        }

        @Test(expected = NumberFormatException.class)
        public void testGetSizeProperty3() throws Exception {

            Map<String,String> props = new HashMap<>();
            OctopusProperties octprop = new OctopusProperties(props);

            octprop.getSizeProperty("K");
        }

        @Test
        public void testGetSizePropertyStringLong() throws Exception {

            Map<String,String> props = new HashMap<>();
            props.put("B", "100");

            OctopusProperties octprop = new OctopusProperties(props);

            long result = octprop.getSizeProperty("B", 999);

            assertTrue(result == 100L);

            result = octprop.getSizeProperty("X", 999);

            assertTrue(result == 999L);
        }

        @Test
        public void testGetStringList_emptyValue_emptyList() throws Exception {
            OctopusProperties octprop = new OctopusProperties();

            String[] result = octprop.getStringList("key");
            String[] expected = new String[] {};
            assertTrue(Arrays.equals(result, expected));
        }

        @Test
        public void testGetStringList_emptyValue_Defaults() throws Exception {
            OctopusProperties octprop = new OctopusProperties();
            String[] defaults = new String[] { "value1", "value2", "value3" };
            String[] result = octprop.getStringList("key", ", ", defaults);

            String[] expected = new String[] { "value1", "value2", "value3" };
            assertTrue(Arrays.equals(result, expected));
        }

        @Test
        public void testGetString_withDefaultDelimiter() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "value1, value2, value3");
            OctopusProperties octprop = new OctopusProperties(props);

            String[] result = octprop.getStringList("key");

            String[] expected = new String[] { "value1", "value2", "value3" };
            assertTrue(Arrays.equals(result, expected));
        }

        @Test
        public void testGetString_withDelimiter() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "value1, value2, value3");
            OctopusProperties octprop = new OctopusProperties(props);

            String[] result = octprop.getStringList("key", ", ");

            String[] expected = new String[] { "value1", "value2", "value3" };
            assertTrue(Arrays.equals(result, expected));
        }

        @Test
        public void testFilter_filledWithKeyAndItem_returnsKey() throws Exception {
            OctopusProperties octprop = getSample();

            OctopusProperties noctprop = octprop.filter("k");

            assertEquals(noctprop.toString(), "OctopusProperties [properties={key=value}]");
        }

        @Test
        public void testFilter_null() throws Exception {
            OctopusProperties octprop = getSample();
            OctopusProperties noctprop = octprop.filter(null);
            assertEquals(noctprop.toString(), "OctopusProperties [properties={key=value, item=value2}]");
        }

        @Test
        public void testFilter_emptyPrefix_returnsSame() throws Exception {
            OctopusProperties octprop = getSample();

            OctopusProperties noctprop = octprop.filter("");

            assertEquals(noctprop, octprop);
        }

        @Test
        public void testPrintProperties() throws Exception {

            Map<String,String> props = new HashMap<>();
            props.put("key", "value");
            props.put("item", "value2");
            OctopusProperties octprop = new OctopusProperties(props);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            octprop.printProperties(new PrintStream(out), "");

            String s = out.toString();

            assertTrue(s.equals("key = value\nitem = value2\n"));

            out = new ByteArrayOutputStream();
            octprop.printProperties(new PrintStream(out), "NOOT");

            s = out.toString();

            assertTrue(s.equals(""));

            out = new ByteArrayOutputStream();
            octprop.printProperties(new PrintStream(out), "key");

            s = out.toString();

            assertTrue(s.equals("key = value\n"));

            out = new ByteArrayOutputStream();
            octprop.printProperties(new PrintStream(out), null);

            s = out.toString();

            assertTrue(s.equals("key = value\nitem = value2\n"));
        }

        @Test
        public void testToString() throws Exception {
            OctopusProperties octprop = getSample();

            assertEquals(octprop.toString(), "OctopusProperties [properties={key=value, item=value2}]");
        }

        public OctopusProperties getSample() throws Exception {
            Map<String,String> props = new HashMap<>();
            props.put("key", "value");
            props.put("item", "value2");
            OctopusProperties octprop = new OctopusProperties(props);
            return octprop;
        }

        @Test
        public void testGetPropertyNames() throws Exception {
            OctopusProperties octprop = getSample();
            String[] names = octprop.getPropertyNames();
            String[] expected_names = new String[] { "item", "key" };
            assertTrue(Arrays.equals(names, expected_names));
        }

        @Test
        public void testEqualsObject() throws Exception {

            OctopusProperties octprop1 = getSample();

            boolean b = octprop1.equals(octprop1);

            assertTrue(b);

            OctopusProperties octprop2 = getSample();

            b = octprop1.equals(octprop2);

            assertTrue(b);

            Map<String,String> props = new HashMap<>();
            props.put("key", "value");

            OctopusProperties octprop3 = new OctopusProperties(props);
            b = octprop1.equals(octprop3);

            assertFalse(b);
        }

        @Test
        public void testSetProperty_Unsupported() throws Exception {
            OctopusProperties octprop = new OctopusProperties();
            try {
                octprop.setProperty("key", "value");
            } catch (UnsupportedOperationException e) {
                assertTrue(e.getMessage().equals("setting properties unsupported in ImmutableTypedProperties"));
            }
        }

        @Test
        public void testPutObject_Unsupported() throws Exception {
            OctopusProperties octprop = new OctopusProperties();
            try {
                octprop.put("key", "value");
            } catch (UnsupportedOperationException e) {
                assertTrue(e.getMessage().equals("setting properties unsupported in ImmutableTypedProperties"));
            }
        }

        @Test
        public void testRemove_Unsupported() throws Exception {
            OctopusProperties octprop = new OctopusProperties();
            try {
                octprop.remove("key");
            } catch (UnsupportedOperationException e) {
                assertTrue(e.getMessage().equals("setting properties unsupported in ImmutableTypedProperties"));
            }
        }

        @Test
        public void testPutAllMapOfQextendsObjectQextendsObject() throws Exception {

            OctopusProperties octprop = new OctopusProperties();

            Map<String, String> tmp = new HashMap<String, String>();
            tmp.put("key", "value");

            try {
                octprop.putAll(tmp);
            } catch (UnsupportedOperationException e) {
                assertTrue(e.getMessage().equals("setting properties unsupported in ImmutableTypedProperties"));
            }
        }

        @Test
        public void testLoad_Reader_Unsupported() throws Exception {
            OctopusProperties octprop = new OctopusProperties();
            try {
                octprop.load(mock(Reader.class));
            } catch (UnsupportedOperationException e) {
                assertTrue(e.getMessage().equals("setting properties unsupported in ImmutableTypedProperties"));
            }
        }

        @Test
        public void testLoad_InputStream_Unsupported() throws Exception {
            OctopusProperties octprop = new OctopusProperties();
            try {
                octprop.load(mock(InputStream.class));
            } catch (UnsupportedOperationException e) {
                assertTrue(e.getMessage().equals("setting properties unsupported in ImmutableTypedProperties"));
            }
        }

        @Test
        public void testLoadFromXML_Unsupported() throws Exception {
            OctopusProperties octprop = new OctopusProperties();
            try {
                octprop.loadFromXML(mock(InputStream.class));
            } catch (UnsupportedOperationException e) {
                assertTrue(e.getMessage().equals("setting properties unsupported in ImmutableTypedProperties"));
            }
        }

    *
    */
}