package tests.sort;

import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReflectionHelper
{
  public ReflectionHelper() {}
  
  public static List<Class<?>> findClassesImplementing(Class<?> interfaceClass, Package fromPackage)
  {
    if (interfaceClass == null) {
      System.out.println("Unknown subclass.");
      return null;
    }
    
    if (fromPackage == null) {
      System.out.println("Unknown package.");
      return null;
    }
    
    List<Class<?>> rVal = new ArrayList();
    try {
      Class[] targets = getAllClassesFromPackage(fromPackage.getName());
      if (targets != null) {
        for (Class<?> aTarget : targets) {
          if (aTarget != null)
          {

            if (!aTarget.equals(interfaceClass))
            {


              if (interfaceClass.isAssignableFrom(aTarget))
              {



                rVal.add(aTarget); }
            }
          }
        }
      }
    } catch (ClassNotFoundException e) {
      System.out.println("Error reading package name.");
    }
    catch (java.io.IOException e)
    {
      System.out.println("Error reading classes in package.");
    }
    

    return rVal;
  }
  






  private static Class<?>[] getAllClassesFromPackage(String packageName)
    throws ClassNotFoundException, java.io.IOException
  {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    assert (classLoader != null);
    String path = packageName.replace('.', '/');
    java.util.Enumeration<URL> resources = classLoader.getResources(path);
    List<File> dirs = new ArrayList();
    while (resources.hasMoreElements()) {
      URL resource = (URL)resources.nextElement();
      dirs.add(new File(resource.getFile()));
    }
    ArrayList<Class<?>> classes = new ArrayList();
    for (File directory : dirs) {
      classes.addAll(findClasses(directory, packageName));
    }
    return (Class[])classes.toArray(new Class[classes.size()]);
  }
  






  private static List<Class<?>> findClasses(File directory, String packageName)
    throws ClassNotFoundException
  {
    List<Class<?>> classes = new ArrayList();
    if (!directory.exists()) {
      return classes;
    }
    File[] files = directory.listFiles();
    for (File file : files) {
      if (file.isDirectory()) {
        assert (!file.getName().contains("."));
        classes.addAll(findClasses(file, packageName + "." + file.getName()));
      }
      else if (file.getName().endsWith(".class")) {
        classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
      }
    }
    return filterConcerteClasses(classes);
  }
  
  private static List<Class<?>> filterConcerteClasses(List<Class<?>> classes)
  {
    List<Class<?>> filteredClasses = null;
    
    for (Class<?> fetchedClass : classes) {
      Integer modifiers = Integer.valueOf(fetchedClass.getModifiers());
      
      if ((!java.lang.reflect.Modifier.isInterface(modifiers.intValue())) && (!java.lang.reflect.Modifier.isAbstract(modifiers.intValue())) && (java.lang.reflect.Modifier.isPublic(modifiers.intValue()))) {
        if (filteredClasses == null) {
          filteredClasses = new ArrayList();
        }
        
        filteredClasses.add(fetchedClass);
      }
    }
    
    return filteredClasses;
  }
}
