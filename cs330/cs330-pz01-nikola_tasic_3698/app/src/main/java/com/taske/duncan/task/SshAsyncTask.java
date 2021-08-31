package com.taske.duncan.task;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.taske.duncan.entity.Command;
import com.taske.duncan.entity.Server;

import java.io.ByteArrayOutputStream;
import java.util.function.Consumer;

public class SshAsyncTask extends AsyncTask<Void, String, String> {
    int port = 22;
    String user = "root";
    String host = null;
    String pass = null;
    String privateKey = null;
    String publicKey = null;
    String command = "echo 1\n";
    Consumer<String> outputCallback = data -> {};
    Consumer<String> progressCallback = data -> {};
    Consumer<String> errorCallback = data -> {};
    private long timeout = 10000;

    public SshAsyncTask(Server server) {
        if (server.getUser() != null)
            this.user = server.getUser();
        this.host = server.getHost();
        this.privateKey = server.getPrivateKey();
        this.publicKey = server.getPublicKey();
        this.pass = server.getPassword();
        this.port = server.getPort();
    }

    public SshAsyncTask(Command command) {
        this(command.getServer());
        this.command = command.getCommand();
    }

    @Override
    protected String doInBackground(Void... args) {
        try {
            return doTask();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SshAsyncTask onOutput(@NonNull Consumer<String> handler) {
        this.outputCallback = handler;
        return this;
    }

    public SshAsyncTask onProgress(@NonNull Consumer<String> handler) {
        this.progressCallback = handler;
        return this;
    }

    public SshAsyncTask onError(@NonNull Consumer<String> handler) {
        this.errorCallback = handler;
        return this;
    }

    public SshAsyncTask timeout(long timeout) {
        this.timeout = timeout;
        return this;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        progressCallback.accept(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        outputCallback.accept(s);
        notifySessionEnd();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        notifySessionStart();
    }

    private void notifySessionStart() {
        onProgressUpdate("STARTING SSH SESSION");
    }

    private void notifySessionEnd() {
        onProgressUpdate("ENDING SSH SESSION");
    }

    private void notifyError(String output) {
        onPostExecute(output);
    }

    private String doTask() {
        ChannelExec channel = null;
        Session session = null;

        try {
            JSch connector = new JSch();
            if (privateKey != null)
                connector.addIdentity(privateKey);

            session = connector.getSession(user, host, port);
            session.setPassword(pass);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            // setup command
            channel = getExecChannel(session);
            channel.setCommand(command);
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();

            waitForCommand(channel);

            // read output
            return responseStream.toString();
        } catch (JSchException ex) {
            ex.printStackTrace();
            if (errorCallback != null)
                errorCallback.accept(ex.getMessage());
            notifyError(ex.getMessage());
            return null;
        } finally {
            if (session != null)
                session.disconnect();
            if (channel != null)
                channel.disconnect();
        }
    }

    private ChannelExec getExecChannel(Session session) throws JSchException {
        return (ChannelExec) session.openChannel("exec");
    }

    private void waitForCommand(ChannelExec channel) {
        long counter = timeout;
        while (channel.isConnected() && --counter > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
