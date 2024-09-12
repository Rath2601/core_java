package javaversionfeatures.java17;

public sealed class SealedClass permits Class1 , Class2, Class3{

}

non-sealed class Class1 extends SealedClass{
	
}

final class Class2 extends SealedClass{
	
}


final class Class3 extends SealedClass{
	
}