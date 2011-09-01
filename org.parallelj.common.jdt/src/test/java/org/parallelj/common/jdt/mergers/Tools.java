/*
 *     ParallelJ, framework for parallel computing
 *     
 *     Copyright (C) 2010 Atos Worldline or third-party contributors as
 *     indicated by the @author tags or express copyright attribution
 *     statements applied by the authors.
 *     
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Lesser General Public
 *     License as published by the Free Software Foundation; either
 *     version 2.1 of the License.
 *     
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *     Lesser General Public License for more details.
 *     
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
package org.parallelj.common.jdt.mergers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Tools {

	public static final String TEST_PATH = "src/test/java/org/parallelj/common/jdt/mergers/";

	public static final String INPUT_TEST_PATH = TEST_PATH + "input/";

	public static final String OUTPUT_TEST_PATH = TEST_PATH + "output/";

	/**
	 * Read content of a file as String.
	 * 
	 * @param path
	 *            Path to the file
	 * @return Content as text of this file
	 * @throws FileNotFoundException
	 *             If the file cannot be found
	 * @throws IOException
	 *             If an error occurs during file processing
	 */
	public static final String getFileContent(String path) throws FileNotFoundException,
			IOException {
		RandomAccessFile raf = null;

		try {
			raf = new RandomAccessFile(path, "r");
			byte[] contentAsCharArray = new byte[(int) raf.length()];
			raf.readFully(contentAsCharArray);
			return new String(contentAsCharArray);
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException ioe) {
				}
			}
		}
	}

}
