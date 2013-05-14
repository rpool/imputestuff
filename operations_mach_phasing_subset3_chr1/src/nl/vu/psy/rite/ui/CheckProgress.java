// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CheckProgress.java

package nl.vu.psy.rite.ui;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class CheckProgress
{

    public CheckProgress()
    {
    }

    public static void main(String args[])
        throws UnknownHostException, MongoException
    {
    	String host = "fluke.psy.vu.nl";
		int port = 989;
		String dbName = "impute";
		Mongo mongo = new Mongo(host, port);
		DB db = mongo.getDB(dbName);
		db.authenticate();
        DBCollection recipeCollection = db.getCollection("recipes");
        BasicDBObject q = new BasicDBObject();
        long total = recipeCollection.count(q);
        q = new BasicDBObject();
        q.put("completed", Boolean.valueOf(true));
        q.put("failed", Boolean.valueOf(false));
        long completed = recipeCollection.count(q);
        q = new BasicDBObject();
        q.put("completed", Boolean.valueOf(true));
        q.put("failed", Boolean.valueOf(true));
        long failed = recipeCollection.count(q);
        q = new BasicDBObject();
        q.put("completed", Boolean.valueOf(false));
        q.put("clientid", new BasicDBObject("$type", Integer.valueOf(2)));
        long locked = recipeCollection.count(q);
        q = new BasicDBObject();
        q.put("completed", Boolean.valueOf(false));
        q.put("clientid", new BasicDBObject("$type", Integer.valueOf(10)));
        long unlocked = recipeCollection.count(q);
        System.out.println("Progress report: ");
        System.out.println((new StringBuilder("Total: ")).append(total).toString());
        System.out.println((new StringBuilder("Completed: ")).append(completed).toString());
        System.out.println((new StringBuilder("Failed: ")).append(failed).toString());
        System.out.println((new StringBuilder("Locked: ")).append(locked).toString());
        System.out.println((new StringBuilder("Unlocked: ")).append(unlocked).toString());
        System.out.println();
    }
}
