package edu.sjsu.cmpe.cache.client;

import java.util.List;
import java.util.ArrayList;
import com.google.common.hash.Hashing;
public class Client {

    public static void main(String[] args) throws Exception {
       
        char [] values = {'a','b','c','d','e','f','g','h','i','j'};

        List<CacheServiceInterface> servers = new ArrayList();
	System.out.println("Starting Cache Client...");
	servers.add(new DistributedCacheService("http://localhost:3000"));
	servers.add(new DistributedCacheService("http://localhost:3001"));
	servers.add(new DistributedCacheService("http://localhost:3002"));
	System.out.println("servers-" +servers);
        for  (int i=1; i <= (values.length); i++)
{       int bucket = Hashing.consistentHash(Hashing.md5().hashInt(i), servers.size());
	servers.get(bucket).put(i,Character.toString(values[i-1]));
	System.out.println("(put " + i + " => " + values[i-1] + ")");
}

        for  (int i=1; i <= (values.length); i++)
{       int bucket = Hashing.consistentHash(Hashing.md5().hashInt(i), servers.size());
	

	servers.get(bucket).get(i);
	System.out.println("(get " + i + " => " + values[i-1] + ")");
	System.out.println("Existing Cache Client...");
     
}

        
    }

}
