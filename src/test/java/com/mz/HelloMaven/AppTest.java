package com.mz.HelloMaven;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * maven test
              *  如果当前的项目是Maven项目的话，则运行Maven text的话
              *  会运行 XXXTest类中的 testxxx 方法
     */
    
    public void testApp()
    {
    	System.out.println("运行 - - >testApp");
    }
    
    
    public void testRound() {
    	System.out.println(Math.round(-15.61));
    	new Thread(()->{
    		System.out.println("ss");
    	}).start(); 
    }
}
