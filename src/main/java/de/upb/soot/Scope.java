package de.upb.soot;

import de.upb.soot.namespaces.INamespace;
import de.upb.soot.signatures.JavaClassSignature;
import de.upb.soot.signatures.PackageSignature;
import de.upb.soot.util.NotYetImplementedException;

/**
 * Definition of a scope
 *
 * @author Linghui Luo
 * @author Ben Hermann
 */
public class Scope {

  /**
   * Define a scope consists of multiple namespaces.
   * 
   * @param namespaces
   */
  public Scope(INamespace... namespaces) {
    // TODO Auto-generated constructor stub
  }

  /**
   * Define a scope consists of multiple packages.
   * 
   * @param packages
   */
  public Scope(PackageSignature... packages) {
    // TODO Auto-generated constructor stub
  }

  /**
   * Define a scope consists of multiple classes.
   * 
   * @param classSignatures
   */
  public Scope(JavaClassSignature... classSignatures) {
    // TODO Auto-generated constructor stub
  }

  public Scope withStartingSignature(JavaClassSignature classSignature) {
    throw new NotYetImplementedException();
  }
}
