/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Jacob
 */

public class FirebaseDb extends Database {
/*
    List<Observer> observers = new ArrayList<>();
    Firestore db = null;

    public FirebaseDb() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("drawing-secret.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(is))
                .setDatabaseUrl("https://drawing-app-13f43.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();
    }

    private void removeListener() {
        Query query = db.collection("drawings");
        ListenerRegistration registration = query.addSnapshotListener(
                new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot t, FirestoreException fe) {
                System.out.println("firestore exception");
            }
            // ...
        });

// Stop listening to changes
        registration.remove();
    }*/
}
