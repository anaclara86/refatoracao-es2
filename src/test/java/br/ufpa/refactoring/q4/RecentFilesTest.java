package br.ufpa.refactoring.q4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecentFilesTest {

    @Test
    void addFile_shouldKeepMostRecentFirst_andRespectLimit() {
        RecentFiles rf = new RecentFiles(3);

        rf.addFile("/a");
        rf.addFile("/b");
        rf.addFile("/c");
        rf.addFile("/d");

        assertEquals(3, rf.size());
        assertEquals("/d", rf.get(0));
        assertEquals("/c", rf.get(1));
        assertEquals("/b", rf.get(2));
    }

    @Test
    void addFile_duplicateShouldMoveToTopWithoutDuplication() {
        RecentFiles rf = new RecentFiles(3);

        rf.addFile("/a");
        rf.addFile("/b");
        rf.addFile("/c");
        rf.addFile("/a"); // Reabre /a, deve ir para o topo

        assertEquals(3, rf.size());
        assertEquals("/a", rf.get(0));
        assertEquals("/c", rf.get(1));
        assertEquals("/b", rf.get(2));
    }

    @Test
    void refactorGoal_replaceInheritanceWithDelegation() {
        RecentFiles rf = new RecentFiles(2);

        rf.addFile("/doc1");
        rf.addFile("/doc2");
        rf.addFile("/doc3");

        assertEquals(2, rf.size());
        assertEquals("/doc3", rf.get(0));
        assertEquals("/doc2", rf.get(1));
    }
}
